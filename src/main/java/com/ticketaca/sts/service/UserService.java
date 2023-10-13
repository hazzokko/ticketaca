package com.ticketaca.sts.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ticketaca.sts.dao.OrderDAO;
import com.ticketaca.sts.dao.UserDAO;

import com.ticketaca.sts.vo.UserVO;

@Service("userService")
@Transactional(propagation = Propagation.REQUIRED)
public class UserService {
	@Autowired
	UserDAO userDAO;
	@Autowired
	OrderDAO orderDAO;
	
	//public List<InternetVO> showAllList() throws DataAccessException
	

	/** jcm / user : 유저 로그인 기능 **/	
	public UserVO userLogin(UserVO userVO) throws DataAccessException {
		return userDAO.userLogin(userVO);
	}//loginUser() END
	
	/** jcm / user : [REST] 회원가입시 아이디 중복확인 기능 **/	
	public boolean duplicatedId(UserVO userVO) throws DataAccessException {
		return userDAO.duplicatedId(userVO);
	}//duplicatedId() END 
	
	/** jcm / user : 유저 회원가입 기능 **/	
	public boolean userSignUp(UserVO userVO) throws DataAccessException {
			return userDAO.userSignUp(userVO);
		}//userSignUp() END 
	
	/** jcm / user :  [REST] 유저 회원탈퇴 기능 **/
	public boolean userOut(UserVO userVO) throws DataAccessException {
		return userDAO.userOut(userVO);
	}//userOut() END
	
	/** jcm / user : 마이페이지 수정내용 업데이트 후, 업데이트 된 내용 표시**/
	public UserVO userUpdate(UserVO userVO) throws DataAccessException {
		userDAO.userUpdate(userVO);
		return userDAO.userSelect(userVO.getUserId());		
	}//userUpdate() END
	
	
	
	
	//-----
	
	
	
	

		
	
}
