package com.ticketaca.sts.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ticketaca.sts.service.OrderService;
import com.ticketaca.sts.vo.ExhVO;
import com.ticketaca.sts.vo.OrderVO;
import com.ticketaca.sts.vo.UserVO;

@Controller("orderController")
@RequestMapping("/order")
@EnableAspectJAutoProxy
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	OrderService orderService;
	HttpSession session;
	
	/* admin : 회원 전체 주문 목록 */
	@RequestMapping(value="/orderAllList", method=RequestMethod.GET)
	public ModelAndView orderAllList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		
		logger.info("orderAllList"+viewName);
		logger.debug("orderAllList"+viewName);
		
		System.out.println("Controller"+viewName);
		
		List<OrderVO> allList = orderService.orderAllList();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("allList", allList);
		
		System.out.println("Controller"+mav);
		
		return mav; 
	}
	
	// admin : 전체 결제목록 검색
		@RequestMapping(value="/searchOrder", method = RequestMethod.GET)
		public ModelAndView searchOrder(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			String viewName = getViewName(request);
			logger.info("searchOrder :" + viewName);
				
			String type = request.getParameter("type");
			String keyword = request.getParameter("keyword");
				
			List<OrderVO> allList = orderService.searchOrder(type, keyword);
			ModelAndView mav = new ModelAndView(viewName);
			mav.addObject("allList", allList);
						
			return mav;
		}
	
	// user : (유저 결제내역 조회) 상단 탭에서 '결제내역' 조회 하기
	@RequestMapping(value="/userOrder", method = RequestMethod.GET)
	public ModelAndView userOrderList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		HttpSession session = request.getSession();
		UserVO userInfo = (UserVO) session.getAttribute("user");
		String userId = userInfo.getUserId();

		System.out.println("userOrderList 의 아이디 : "+ userId);	// 테스트 코드
		
		List<OrderVO> userOrderList = orderService.userOrderList(userId);

		System.out.println("userOrderList 의 주문목록 " + userOrderList.toString());	// 테스트 코드
		
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("userOrderList", userOrderList);
		return mav;
	}
	
	// user : (예매하기)상세 제품 페이지에서 결제 화면으로 출력
	@RequestMapping(value="/getOrder", method = RequestMethod.GET)
	public ModelAndView getOrder(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// @Requestparam
		String viewName = getViewName(request);	
		
		logger.info("getOrder : " + viewName);		
		logger.debug("getOrder : " + viewName);	
		

		int exhNo = Integer.parseInt(request.getParameter("exhNo"));
		ExhVO detailExh = orderService.getOrder(exhNo);		
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("detailExh", detailExh);
		return mav;
	}
	
	// user : 상품 예매(결제)등록 하기
	@RequestMapping(value="/addOrder", method=RequestMethod.POST)
	public ModelAndView addOrder(@ModelAttribute("order") OrderVO orderVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("sadfasdfasfsadfasdfasdfasdfadsf");
		System.out.println(orderVO);
		String viewName = getViewName(request);
		HttpSession session = request.getSession();
		UserVO userInfo = (UserVO) session.getAttribute("user");
		String userId = userInfo.getUserId();
		
		System.out.println("addOrder userId : " + userId);
		
		orderVO.setUserId(userId);
		orderService.addOrder(orderVO);
		
		ModelAndView mav = new ModelAndView("redirect:/order/userOrder");
		
		return mav;
	}
	
	//총명  user : 상품 결제창 이동
		@RequestMapping(value="/payment", method=RequestMethod.POST)
		public ModelAndView orderPayment(@ModelAttribute("order") OrderVO orderVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
			String viewName = getViewName(request);
			HttpSession session = request.getSession();
			UserVO userInfo = (UserVO) session.getAttribute("user");
			String userId = userInfo.getUserId();
			String userName = userInfo.getUserName();
			orderVO.setUserId(userId);
			orderVO.setUserName(userName);
			System.out.println(orderVO);
			ModelAndView mav = new ModelAndView(viewName);
			mav.addObject("order", orderVO);
			return mav;
		}
	
		
	
	/**************************************/
	/**viewName 추출**/
	 private String getViewName(HttpServletRequest request) throws Exception {
			String contextPath = request.getContextPath();
			String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
			if (uri == null || uri.trim().equals("")) {
				uri = request.getRequestURI();
			}

			int begin = 0;
			if (!((contextPath == null) || ("".equals(contextPath)))) {
				begin = contextPath.length();
			}

			int end;
			if (uri.indexOf(";") != -1) {
				end = uri.indexOf(";");
			} else if (uri.indexOf("?") != -1) {
				end = uri.indexOf("?");
			} else {
				end = uri.length();
			}

			String viewName = uri.substring(begin, end);
			if (viewName.indexOf(".") != -1) {
				viewName = viewName.substring(0, viewName.lastIndexOf("."));
			}
			if (viewName.lastIndexOf("/") != -1) {
				viewName = viewName.substring(viewName.lastIndexOf("/", 1), viewName.length());
			}
			return viewName;
		}

	

}
