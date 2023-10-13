package com.ticketaca.sts.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ticketaca.sts.vo.ExhVO;
import com.ticketaca.sts.vo.OrderVO;
import com.ticketaca.sts.vo.UserVO;

@Repository("orderDAO")
public class OrderDAO {

	@Autowired
	private SqlSession sqlSession;
	OrderVO orderVO;

	// admin : 회원 전체 주문목록
	public List<OrderVO> orderAllList() throws DataAccessException {
		List<OrderVO> AllList = sqlSession.selectList("mapper.ticketaca.orderAllList");
		for (OrderVO orderVO : AllList) {
	        int ordTQnt = orderVO.getOrdAQnt() + orderVO.getOrdYQnt() + orderVO.getOrdCQnt();
	        orderVO.setOrdTQnt(ordTQnt);
	    }
		return AllList;
	}
	
	// admin : 주문목록 ->  주문검색
	public List<OrderVO> searchOrder(String type, String keyword) throws DataAccessException {
		if (type.equals("orderNo")) {
			return sqlSession.selectList("mapper.ticketaca.searchOrderbyOrderNo", keyword);
		}if (type.equals("exhNo")) {
			return sqlSession.selectList("mapper.ticketaca.searchOrderbyExhNo", keyword);
		}if (type.equals("userId")) {
			return sqlSession.selectList("mapper.ticketaca.searchOrderbyUserId", keyword);
		}
		return null;
	}
	
	// user : 결제목록(예매목록) 조회하기
	public List<OrderVO> userOrderList(String userId) throws DataAccessException {
		List<OrderVO> userOrderList = sqlSession.selectList("mapper.ticketaca.getUserOrders", userId);
		for (OrderVO orderVO : userOrderList) {
	        int ordTQnt = orderVO.getOrdAQnt() + orderVO.getOrdYQnt() + orderVO.getOrdCQnt();
	        orderVO.setOrdTQnt(ordTQnt);
	    }		
		return userOrderList;
	}
	
	// user : 예매하기
	public ExhVO getOrder(int exhNo) throws DataAccessException {
		ExhVO getOrder = sqlSession.selectOne("mapper.ticketaca.getOrder", exhNo);
		return getOrder;
	}
	
	// user : 예매완료(결제완료) -> order 테이블 insert
	public int addOrder(OrderVO orderVO) throws DataAccessException {
		int result = sqlSession.insert("mapper.ticketaca.newOrder", orderVO);
		return result;
	}
	

}
