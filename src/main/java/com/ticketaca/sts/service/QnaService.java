package com.ticketaca.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ticketaca.sts.dao.QnaDAO;
import com.ticketaca.sts.vo.QnaVO;

import oracle.jdbc.OracleConnection.CommitOption;

@Service("qnaService")

@Transactional
public class QnaService {

	public QnaService() {
		System.out.println("QnaService()");
	}

	@Autowired
	public QnaDAO qnaDAO;

	public List adminlistQna() throws DataAccessException {
		return qnaDAO.adminlistQna();
	}

	public QnaVO admindetailQna(Integer qnaNo) {
		return qnaDAO.admindetailQna(qnaNo);
	}

	public QnaVO selectPno(Integer qnaNo) {
		return qnaDAO.selectPno(qnaNo);
	}

	public void adminWriteQna(QnaVO qnaVO) {
		qnaDAO.adminWriteQna(qnaVO);
	}

	public void adminUpdateRead(int qnaNo) {
		qnaDAO.adminUpdateRead(qnaNo);
	}

	public List<QnaVO> userListQna(String userId) {
		return qnaDAO.userListQna(userId);
	}

	public void userWriteQna(QnaVO qnaVO) {
		qnaDAO.userWriteQna(qnaVO);
	}

	public QnaVO userDetailQna(QnaVO qnaVO) {
		return qnaDAO.userDetailQna(qnaVO);
	}

	public void userModfiyQna(QnaVO qnaVO) {
		qnaDAO.userModfiyQna(qnaVO);
		
	}

	public QnaVO DetailContent(QnaVO qnaVO) {
		return qnaDAO.DetailContent(qnaVO);
	}
}
