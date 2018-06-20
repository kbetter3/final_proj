package spring.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import spring.bean.MusicPlayGroupDto;

@Repository
public interface MusicPlayGroupDao {
	public List<MusicPlayGroupDto> getListByPlayDateMonthly();
	public List<MusicPlayGroupDto> getListByPlayDateWeekly();
	public List<MusicPlayGroupDto> getListByPlayDateDaily();
}
