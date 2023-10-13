package com.ticketaca.sts.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ticketaca.sts.vo.AdminVO;
import com.ticketaca.sts.vo.ExhVO;
import com.ticketaca.sts.vo.OrderVO;
import com.ticketaca.sts.vo.QnaVO;
import com.ticketaca.sts.vo.UserVO;

@Repository("adminDAO")
public class AdminDAO {

	@Autowired
	private SqlSession sqlSession;


	/** jcm / admin : 관리자 로그인 기능 **/
	public AdminVO adminLogin(AdminVO adminVO) throws DataAccessException{
		return sqlSession.selectOne("mapper.ticketaca.adminLogin", adminVO);
	}
	
	/** jcm / admin : 관리자 유저목록 조회 **/
	public List<UserVO> adminUserList() throws DataAccessException{
		return sqlSession.selectList("mapper.ticketaca.adminUserList");
	}
	
	/** jcm / admin : 관리자 유저목록  - 유저검색  **/
	public List<UserVO> adminUserSearch(String condition, String keyword) throws DataAccessException{
		if(condition.equals("userId")){
			return sqlSession.selectList("mapper.ticketaca.adminUserSearchById", keyword);
		}
		if(condition.equals("userName")){
			return sqlSession.selectList("mapper.ticketaca.adminUserSearchByName", keyword);
		}
		if(condition.equals("userTel")){
			return sqlSession.selectList("mapper.ticketaca.adminUserSearchByTel", keyword);
		}
		if(condition.equals("userEmail")){
			return sqlSession.selectList("mapper.ticketaca.adminUserSearchByEmail", keyword);
		}
		return null;
	}
	
	

	
}//CLASS end
