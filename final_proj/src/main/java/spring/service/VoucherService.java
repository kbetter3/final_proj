package spring.service;

import org.springframework.stereotype.Service;

@Service
public interface VoucherService {
	public boolean isExpired(String uid);
	
	public void addVoucher(String uid, int day);
	public void setVoucher(String uid, int day);
}
