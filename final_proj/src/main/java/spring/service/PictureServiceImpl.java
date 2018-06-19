package spring.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import spring.bean.RespState;

@Service("pictureService")
public class PictureServiceImpl implements PictureService {
//	String fileDirPrefix = "C";
	String fileDirPrefix = "D";
//	String fileDirPrefix = "E";
	
	@Override
	public String saveTempPic(MultipartFile mFile) throws IllegalStateException, IOException {
		String fileDir = fileDirPrefix + ":/tempPic";
		
		return savePic(fileDir, mFile);
	}

	@Override
	public String saveArtistPic(MultipartFile mFile) throws IllegalStateException, IOException {
		String fileDir = fileDirPrefix + ":/artistPic";
		
		return savePic(fileDir, mFile);
	}

	@Override
	public String saveAlbumPic(MultipartFile mFile) throws IllegalStateException, IOException {
		String fileDir = fileDirPrefix + ":/albumPic";
		
		return savePic(fileDir, mFile);
	}

	@Override
	public String savePic(String fileDir, MultipartFile mFile) throws IllegalStateException, IOException {
		String rname = System.currentTimeMillis() + "-" + UUID.randomUUID();
		String fname = mFile.getOriginalFilename();
		long fsize = mFile.getSize();
		String ftype = mFile.getContentType();
		
		File target = new File(fileDir, rname);
		
		mFile.transferTo(target);
		
		
		return rname;
	}

	@Override
	public ByteArrayResource loadTempPic(String filename) throws IOException {
		String fileDir = fileDirPrefix + ":/tempPic";
		
		return loadPic(fileDir, filename);
	}

	@Override
	public ByteArrayResource loadArtistPic(String filename) throws IOException {
		String fileDir = fileDirPrefix + ":/artistPic";
		
		return loadPic(fileDir, filename);
	}

	@Override
	public ByteArrayResource loadAlbumPic(String filename) throws IOException {
		String fileDir = fileDirPrefix + ":/albumPic";
		
		return loadPic(fileDir, filename);
	}

	@Override
	public ByteArrayResource loadPic(String fileDir, String filename) throws IOException {
		File f = new File(fileDir, filename);
		
		byte[] data = FileUtils.readFileToByteArray(f);
		ByteArrayResource resource = new ByteArrayResource(data);
		
		return resource;
	}

}
