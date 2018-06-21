package spring.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tritonus.share.sampled.file.TAudioFileFormat;

import spring.bean.MainDirectory;
import spring.bean.MemberDto;
import spring.bean.MusicDto;
import spring.bean.MusicPlayDto;
import spring.bean.RespState;
import spring.service.BadReqService;
import spring.service.MemberService;
import spring.service.MusicService;
import spring.service.TagService;
import spring.service.VoucherService;

@Controller
public class PlayerController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MusicService musicService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private BadReqService badReqService;
	
	@Autowired
	private VoucherService voucherService;
	
	
//	@RequestMapping(value="/player")
//	public String player() {
//		return "player";
//	}
	
	@RequestMapping("/player")
	@ResponseBody
	public ResponseEntity<String> playerTag(HttpSession session) throws IOException {
		if (session.getAttribute("uid") != null) {
			// 정상접근 - 로그인한 사용자가 요청한 경우
			return tagService.getTag("player.txt");
		} else {
			// 로그인하지 않은 사용자가 요청한 경우
//			return badReqService.forbiddenReq().body(null);
			JSONObject jobj = new JSONObject();
			jobj.put("state", RespState.MESSAGE);
			jobj.put("msg", "로그인한 사용자만 사용할 수 있습니다.");
			
			return tagService.getEmptyResponse().body(jobj.toString());
		}
	}
	
	@RequestMapping(value="/music")
	@ResponseBody
	public void music(HttpServletRequest request, HttpServletResponse response, @RequestParam String name) throws IOException, UnsupportedAudioFileException {
		
		log.debug("MN = {}", name);
		File file = new File("d:\\mp3\\1529504972062-5fa80b28-aa3b-4754-b5af-69467deff386");
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
	
	@RequestMapping(value="/member/music")
	@Transactional
	@ResponseBody
	public void music(HttpSession session, HttpServletRequest request, HttpServletResponse response, int musicno) throws IOException, UnsupportedAudioFileException {
//		log.debug("MN = {}", name);
		
		MusicPlayDto musicPlayDto = new MusicPlayDto();
		musicPlayDto.setMid((String)session.getAttribute("uid"));
		musicPlayDto.setMusicno(musicno);
		musicService.insert(musicPlayDto);
		
		MusicDto musicDto = musicService.getByNo(musicno);
		
		String uid = (String)session.getAttribute("uid");
		
		boolean isExpired = voucherService.isExpired(uid);
		
		
		
		File file = new File(MainDirectory.DIRECTORY + ":/mp3", musicDto.getMFile());
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
				
				if (isExpired) {
					rangeEnd = (long) (targetSize * rate);						// 1분 재생시 활성화
				} else {
					rangeEnd = Long.parseLong(range.substring(idxm + 1));		// 전체 재생시 활성화
				}
				

				if (rangeStart > 0) {
					isPart = true;
				}
			} else {
				rangeEnd = targetSize - 1;
			}

			
			long partSize;
			if (isExpired) {
				partSize = limit - rangeStart + 1;							// 1분 재생시 활성화
			} else {
				partSize = rangeEnd - rangeStart + 1;						// 전체 재생시 활성화
			}
			

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
