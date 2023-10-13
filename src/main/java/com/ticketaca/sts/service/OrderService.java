package com.ticketaca.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ticketaca.sts.dao.OrderDAO;
import com.ticketaca.sts.vo.ExhVO;
import com.ticketaca.sts.vo.OrderVO;
import com.ticketaca.sts.vo.UserVO;

@Service("orderService")
@Transactional(propagation = Propagation.REQUIRED)
public class OrderService {

	@Autowired
	OrderDAO orderDAO;

	// admin : 회원 전체 주문목록	
	public List<OrderVO> orderAllList() throws DataAccessException {
		List<OrderVO> AllList = orderDAO.orderAllList();
		return AllList;
	}
	
	// admin : 주문목록 검색
	public List<OrderVO> searchOrder(String type, String keyword) throws DataAccessException{
		return orderDAO.searchOrder(type, keyword);
	}
	
	// user : (유저 결제내역 조회) 상단 탭에서 '결제내역' 조회 하기
	public List<OrderVO> userOrderList(String userId) throws DataAccessException {
		List<OrderVO> userOrderList = orderDAO.userOrderList(userId);
		return userOrderList;
	}
	
	// user : (얘매하기) 상품정보에서 얘매하기 눌러 화면에 상품정보 디테일 출력	
	public ExhVO getOrder(int exhNo) throws DataAccessException {
		ExhVO getOrder = orderDAO.getOrder(exhNo);
		return getOrder;
	}
	
	// user : 결제하기 -> 예매완료
	public int addOrder(OrderVO orderVO) throws DataAccessException {
		int result = orderDAO.addOrder(orderVO);
		return result;
	}

}
