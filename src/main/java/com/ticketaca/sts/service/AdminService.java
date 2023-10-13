package com.ticketaca.sts.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.ticketaca.sts.dao.AdminDAO;
import com.ticketaca.sts.dao.OrderDAO;
import com.ticketaca.sts.vo.AdminVO;
import com.ticketaca.sts.vo.ExhVO;
import com.ticketaca.sts.vo.OrderVO;
import com.ticketaca.sts.vo.QnaVO;
import com.ticketaca.sts.vo.UserVO;

@Service("adminService")
@Transactional(propagation = Propagation.REQUIRED)
public class AdminService {
	@Autowired
	AdminDAO adminDAO;
	@Autowired
	OrderDAO orderDAO;
	@Autowired
	AdminDAO adminqnaDAO;
	
	
	//public List<InternetVO> showAllList() throws DataAccessException
	
	/** jcm / admin : 관리자 로그인 **/
	public AdminVO adminLogin(AdminVO adminVO) throws DataAccessException {
		return adminDAO.adminLogin(adminVO);
	}//loginUser() END
	
	/** jcm / admin : 유저 관리 **/
	public List<UserVO> adminUserList() throws DataAccessException {
		return adminDAO.adminUserList();
	}//adminUserList() END
	
	/** jcm / admin : 유저 관리페이지 - 유저검색 **/
	public List<UserVO> adminUserSearch(String condition, String keyword) throws DataAccessException {
		return adminDAO.adminUserSearch(condition, keyword);
	}//adminUserList() END
	

}//Class END
