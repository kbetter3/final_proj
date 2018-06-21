package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.repository.MemberDao;
import spring.repository.SimpleDao;

@Service("voucherService")
public class VoucherServiceImpl implements VoucherService {

	@Autowired
	private SimpleDao simpleDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public boolean isExpired(String uid) {
		if (simpleDao.getVoucherDateCalc(uid).getAnswer().equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void addVoucher(String uid, int day) {
		memberDao.addVoucher(uid, day);
		addDownCount(uid, day);
	}

	@Override
	public void setVoucher(String uid, int day) {
		memberDao.setVoucher(uid, day);
		addDownCount(uid, day);
	}
	
	private void addDownCount(String uid, int downcount) {
		memberDao.addDownCount(uid, downcount);
	}
}
