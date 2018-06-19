package spring.service;

import java.io.IOException;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface PictureService {
	public String saveTempPic(MultipartFile mFile) throws IllegalStateException, IOException;
	public String saveArtistPic(MultipartFile mFile) throws IllegalStateException, IOException;
	public String saveAlbumPic(MultipartFile mFile) throws IllegalStateException, IOException;
	
	public String savePic(String fileDir, MultipartFile mFile) throws IllegalStateException, IOException;
	
	
	public ByteArrayResource loadTempPic(String filename) throws IOException;
	public ByteArrayResource loadArtistPic(String filename) throws IOException;
	public ByteArrayResource loadAlbumPic(String filename) throws IOException;
	
	public ByteArrayResource loadPic(String fileDir, String filename) throws IOException;
}
