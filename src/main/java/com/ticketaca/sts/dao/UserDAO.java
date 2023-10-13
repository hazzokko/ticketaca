package com.ticketaca.sts.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ticketaca.sts.vo.ExhVO;
import com.ticketaca.sts.vo.QnaVO;
import com.ticketaca.sts.vo.UserVO;
import com.ticketaca.sts.vo.ZzimVO;

@Repository("userDAO")
public class UserDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	//public List<InternetVO> selectAll() throws DataAccessException
	
	/** jcm / user : 유저 로그인 **/
	public UserVO userLogin(UserVO userVO) throws DataAccessException{
		return sqlSession.selectOne("mapper.ticketaca.userLogin", userVO);
	}//userLogin() END
	
	/** jcm / user : [REST] 회원가입 아이디 중복확인 **/
	public boolean duplicatedId(UserVO userVO) throws DataAccessException{
		String result = sqlSession.selectOne("mapper.ticketaca.duplicatedId", userVO);
		if(result.equals("true")){
			return true;
		}else{			
			return false;
		}
	}//duplicatedId() END
	
	/** jcm / user : 유저 회원가입 **/
	public boolean userSignUp(UserVO userVO) throws DataAccessException{
		int result = sqlSession.insert("mapper.ticketaca.userSignUp", userVO);
		if(result == 1){
			return true;
		} else {
			return false;
		}
	}//userSignUp() END
	
	/** jcm / user : [REST] 유저 회원탈퇴 **/
	public boolean userOut(UserVO userVO) throws DataAccessException{
		int result = sqlSession.update("mapper.ticketaca.userOut",userVO);
		if(result == 1){
			return true;
		} else {
			return false;
		}
	}//userOut() END
	
	/** jcm / user : 마이페이지 회원정보 수정 **/
	public void userUpdate(UserVO userVO) throws DataAccessException{
		System.out.println("DAOOOOOO: " + userVO.getUserPw());
		int result = sqlSession.update("mapper.ticketaca.userUpdate", userVO);
	}//userUpdate() END
	
	/** jcm / user : 회원ID로 회원 정보조회 **/
	public UserVO userSelect(String userId) throws DataAccessException{
		return sqlSession.selectOne("mapper.ticketaca.userSelect", userId);
	}//userSelect() END
	
	
	
	//------------

}//class END
