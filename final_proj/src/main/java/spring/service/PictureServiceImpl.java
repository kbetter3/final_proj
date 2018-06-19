package spring.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import spring.bean.RespState;

@Service("pictureService")
public class PictureServiceImpl implements PictureService {
	String fileDirPrefix = "C";
	
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

}
