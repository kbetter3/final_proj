package spring.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface PictureService {
	public String saveTempPic(MultipartFile mFile) throws IllegalStateException, IOException;
	public String saveArtistPic(MultipartFile mFile) throws IllegalStateException, IOException;
	public String saveAlbumPic(MultipartFile mFile) throws IllegalStateException, IOException;
	
	public String savePic(String fileDir, MultipartFile mFile) throws IllegalStateException, IOException;
}
