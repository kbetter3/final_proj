package spring.repository;

import org.springframework.stereotype.Repository;

import spring.bean.MusicPlayDto;

@Repository
public interface MusicPlayDao {
	public void insert(MusicPlayDto musicPlayDto);
}
