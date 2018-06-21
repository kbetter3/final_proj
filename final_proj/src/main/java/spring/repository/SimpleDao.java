package spring.repository;

import org.springframework.stereotype.Repository;

import spring.bean.SimpleDto;

@Repository
public interface SimpleDao {
	public SimpleDto getVoucherDateCalc(String uid);
}
