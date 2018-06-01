package spring.test;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.bean.AlbumDto;
import spring.bean.ArtistDto;
import spring.bean.GenreDto;
import spring.bean.MemberDto;
import spring.bean.MusicDto;
import spring.service.AlbumService;
import spring.service.ArtistService;
import spring.service.GenreService;
import spring.service.MemberService;
import spring.service.MusicService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:/spring/application-config.xml"})
public class DBConnectionTest {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private GenreService genreService;
	
	@Test
	public void genreTest() {
		genreService.insert("테스트/용");
		List<GenreDto> list = genreService.getList();
		
		for (GenreDto genreDto : list) {
			log.debug("{}", genreDto);
		}
	}
	
	
	@Autowired
	private ArtistService artistService;
	
	@Test
	public void artistTest() {
		/*
		ArtistDto artistDto = new ArtistDto();
		artistDto.setName("테스트가수");
		artistDto.setMember("멤버1, 멤버2");
		artistDto.setDebutDate("2018/05/31");
		artistDto.setActivityType("그룹");
		artistDto.setAgency("테스트 에이전시");
		artistDto.setThumb("이미지파일이름");
		
		artistService.insert(artistDto);
		
		
		List<ArtistDto> list = artistService.getList();
		*/
		
		List<ArtistDto> list = artistService.getListByName("트");
		
		for (ArtistDto art : list) {
			log.debug("getListByName : {}", art);
		}
		
		ArtistDto artistDto = artistService.getByNo(2);
		
		log.debug("getByNo : {}", artistDto);
	}
	
	
	@Autowired
	private AlbumService albumService;
	
	@Test
	public void albumTest() {
//		AlbumDto albumDto = new AlbumDto();
//		albumDto.setName("테스트 앨범");
//		albumDto.setArtistNo(2);
//		albumDto.setGenre("테스트/용");
//		albumDto.setReleaseDate("2018/05/31");
//		albumDto.setThumb("이미지 이름");
//		
//		albumService.insert(albumDto);
		
		List<AlbumDto> list;
		
		list = albumService.getListByArtistNo(3);
		
		for (AlbumDto album : list) {
			log.debug("getList = {}", album);
		}
		
		AlbumDto albumDto = albumService.getByNo(1);
		log.debug("getByNo = {}", albumDto);
	}
	
	
	@Autowired
	private MusicService musicService;
	
	@Test
	public void MusicTest() {
//		MusicDto musicDto = new MusicDto();
//		
//		musicDto.setName("테스트 노래다");
//		musicDto.setArtistNo(2);
//		musicDto.setAlbumNo(1);
//		musicDto.setGenre("테스트/용");
//		musicDto.setReleaseDate("2018/05/31");
//		
//		musicService.insert(musicDto);
		List<MusicDto> list;
//		list = musicService.getList();
//		list = musicService.getListOrderByReleaseDate();
//		list = musicService.getListByAlbumNo(1);
//		list = musicService.getListByArtistNo(2);
//		list = musicService.getListByGenre("테스트/용");
		list = musicService.getListByName("트");
		
		
		for (MusicDto music : list) {
			log.debug("getList = {}", music);
		}
		
//		MusicDto musicdto = musicService.getByNo(1);
//		log.debug("getByNo = {}", musicDto);
	}
	
	
	@Autowired
	private MemberService memberService;
	
	@Test
	public void MemberTest() throws NoSuchAlgorithmException, MessagingException {
		MemberDto memberDto = new MemberDto();
		memberDto.setId("test01");
		memberDto.setPw("test");
		memberDto.setEmail("kbetter3@naver.com");
		
		memberService.insert(memberDto);
	}
}
