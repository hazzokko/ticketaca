package com.ticketaca.sts.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ticketaca.sts.vo.QnaVO;

@Repository("qnaDAO")
public class QnaDAO {

	public QnaDAO() {
		System.out.println("QnaDAO()");
	}

	@Autowired
	public SqlSession sqlSession;
	
	public List adminlistQna() {
		List<QnaVO> adminlist = null;
		
		adminlist = sqlSession.selectList("mapper.ticketaca.adminlistQna");
		
		return adminlist;
	}

	public QnaVO admindetailQna(Integer qnaNo) {
		QnaVO admindetail = null;
		
		admindetail = sqlSession.selectOne("mapper.ticketaca.adminDetailQna",qnaNo);
		
		return admindetail;
	}

	public QnaVO selectPno(Integer qnaNo) {
		QnaVO selectPno = null;
		
		selectPno = sqlSession.selectOne("mapper.ticketaca.selectPno",qnaNo);
		
		return selectPno;
	}

	public void adminWriteQna(QnaVO qnaVO) {
		sqlSession.insert("mapper.ticketaca.adminWriteQna", qnaVO);
		
	}

	public void adminUpdateRead(int qnaNo) {
		sqlSession.update("mapper.ticketaca.adminUpdateRead",qnaNo);
	}

	public List<QnaVO> userListQna(String userId) {
		List<QnaVO> userList = null;
		userList = sqlSession.selectList("mapper.ticketaca.userListQna",userId);
		return userList;
	}

	public void userWriteQna(QnaVO qnaVO) {
		sqlSession.insert("mapper.ticketaca.userWriteQna",qnaVO);
	}

	public QnaVO userDetailQna(QnaVO qnaVO) {
		QnaVO userDetail = null;
		
		userDetail = sqlSession.selectOne("mapper.ticketaca.userDetailQna",qnaVO);
		return userDetail;
	}
	
	public void userModfiyQna(QnaVO qnaVO) {
		sqlSession.update("mapper.ticketaca.userModfiyQna",qnaVO);
		System.out.println("@#!@"+qnaVO);
	}

	public QnaVO DetailContent(QnaVO qnaVO) {
		QnaVO DetailContent = null;
		DetailContent = sqlSession.selectOne("mapper.ticketaca.DetailContent",qnaVO);
		
		return DetailContent;
	}

	

}
