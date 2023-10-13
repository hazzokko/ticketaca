package com.ticketaca.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ticketaca.sts.dao.ZzimDAO;
import com.ticketaca.sts.vo.ZzimVO;

@Service("zzimService")
@Transactional(propagation = Propagation.REQUIRED)
public class ZzimService {
	
	@Autowired
	private ZzimDAO zzimDAO;


	public List<ZzimVO> zzimList(String userId) throws DataAccessException {
		List<ZzimVO> zzimList = zzimDAO.zzimList(userId);
		return zzimList;
	}
	
	public boolean zzimRemove(int zzNo) throws DataAccessException {
		return zzimDAO.zzimRemove(zzNo);
	}


}
