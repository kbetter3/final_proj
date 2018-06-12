package spring.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tritonus.share.sampled.file.TAudioFileFormat;

@Controller
public class PlayerController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value="/player")
	public String player() {
		return "player";
	}
	
	@RequestMapping(value="/music")
	@ResponseBody
	public void music(HttpServletRequest request, HttpServletResponse response, @RequestParam String name) throws IOException, UnsupportedAudioFileException {
		log.debug("MN = {}", name);
		File file = new File("D:\\spring\\asdfasdf");
		log.debug("f exist = {}", file.exists());
		RandomAccessFile target = new RandomAccessFile(file, "r");
		
		AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
		int duration = 0;
	    if (fileFormat instanceof TAudioFileFormat) {
	        Map<?, ?> properties = ((TAudioFileFormat) fileFormat).properties();
	        String key = "duration";
	        Long microseconds = (Long) properties.get(key);
	        int mili = (int) (microseconds / 1000);
	        int sec = (mili / 1000) % 60;
	        int min = (mili / 1000) / 60;
//	        System.out.println("time = " + min + ":" + sec);
	        log.debug("time = {}:{}", min, sec);
	        duration = min * 60 + sec;
	    }
	    
	    float rate = (float) (60.0 / duration);
	    log.debug("rate = {}", rate);

		Long rangeStart = 0L;
		Long rangeEnd = 0L;
		boolean isPart = false;

		try {
			long targetSize = target.length();
			String range = request.getHeader("range");
			long limit = (long) (targetSize * rate);


			// 브라우저에 따라 range 형식이 다른데, 기본 형식은 "bytes={start}-{end}" 형식이다.
			// range가 null이거나, reqStart가 0이고, end가 없을 경우 전체 요청이다.
			// 요청 범위를 구한다.

			if (range != null) {
				if (range.endsWith("-")) {
					range = range + (targetSize - 1);
				}

				int idxm = range.trim().indexOf("-");	// "-" 위치
				rangeStart = Long.parseLong(range.substring(6, idxm));
//				rangeEnd = Long.parseLong(range.substring(idxm + 1));		// 전체 재생시 활성화
				rangeEnd = (long) (targetSize * rate);						// 1분 재생시 활성화

				if (rangeStart > 0) {
					isPart = true;
				}
			} else {
				rangeEnd = targetSize - 1;
			}

//			long partSize = rangeEnd - rangeStart + 1;						// 전체 재생시 활성화
			long partSize = limit - rangeStart + 1;							// 1분 재생시 활성화

			response.reset();

			response.setStatus(isPart ? 206 : 200);

			response.setContentType("audio/mpeg");
			response.setHeader("Content-Range", "bytes " + rangeStart + "-" + rangeEnd + "/" + targetSize);
			response.setHeader("Accept-Ranges", "bytes");
			response.setHeader("Content-Length", "" + partSize);

			OutputStream out = response.getOutputStream();
			target.seek(rangeStart);

			int bufferSize = 8 * 1024;
			byte[] buf = new byte[bufferSize];

			do {
				int block = partSize > bufferSize ? bufferSize : (int)partSize;
				int len = target.read(buf, 0, block);
				out.write(buf, 0, len);
				partSize -= block;
			} while(partSize > 0);



			log.debug("range = {}", range);
		} finally {
			target.close();
		}
	}
}
