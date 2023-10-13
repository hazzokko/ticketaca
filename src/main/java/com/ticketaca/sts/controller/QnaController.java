package com.ticketaca.sts.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.glassfish.external.statistics.impl.StatsImpl;
import com.ticketaca.sts.service.QnaService;
import com.ticketaca.sts.vo.QnaVO;
import com.ticketaca.sts.vo.UserVO;

@Controller("QnaController")
@RequestMapping("/qna")
//@EnableAspectJAutoProxy
public class QnaController {

	public QnaController() {
		System.out.println("QnaController()");
	}
	
	@Autowired
	private QnaVO qnaVO;
	
	@Autowired
	private QnaService qnaService;
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	/** 호준 - 관리자 qna 모든 리스트**/
	@RequestMapping(value = "/admin/adminlistQna", method = RequestMethod.GET)
	public ModelAndView adminlistQna(HttpServletRequest request,HttpServletResponse response) throws Exception {

		String viewName = getViewName(request);
		
		logger.info("viewName: " + viewName);
		logger.debug("viewName: " + viewName);
		
		List adminlist = qnaService.adminlistQna();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("adminlist",adminlist);
		return mav;
	}
	
	/** 호준 - 관리자 qna 상세보기**/
	@RequestMapping(value = "/admin/admindetailQna", method = RequestMethod.GET)
	public ModelAndView admindetailQna(@RequestParam Integer qnaNo,HttpServletRequest request){
		String viewName = getViewName(request);
		
		logger.info("viewName: " + viewName);
		logger.debug("viewName: " + viewName);
		
		//글 제목 클릭하였을 때 해당 글 가져오기
		QnaVO adminDetail = qnaService.admindetailQna(qnaNo);
		
		//답변의 내용을 가져오기
		QnaVO selectPno = qnaService.selectPno(qnaNo); 
		
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("adminDetail",adminDetail);
		mav.addObject("selectPno",selectPno);
		
		return mav;
	}
	
	/** 호준 - 관리자 qna 답변 창 보내기 **/
	@RequestMapping(value = "/admin/adminWriteQnaForm", method = RequestMethod.POST)
	public ModelAndView adminWriteQna(@RequestParam("Detail_qnaNo") Integer qnaNo ,HttpServletRequest request) throws Exception {

		String viewName = getViewName(request);
		
		logger.info("viewName: " + viewName);
		logger.debug("viewName: " + viewName);
		
		//글 제목 클릭하였을 때 해당 글 가져오기
		QnaVO adminDetail = qnaService.admindetailQna(qnaNo);
				
		//답변의 내용을 가져오기
		QnaVO selectPno = qnaService.selectPno(qnaNo);
		
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("adminDetail",adminDetail);
		mav.addObject("selectPno",selectPno);
		
		return mav;
	}
	
	/** 호준 - 유저 qna 작성 글 리스트 **/
	@RequestMapping(value = "/user/userListQna")
	public ModelAndView userListQna(HttpServletRequest request){
		
		String viewName = getViewName(request);
		
		HttpSession session = request.getSession();
		UserVO userInfo = (UserVO) session.getAttribute("user");
		String userId = userInfo.getUserId();
		
		logger.info("viewName: " + viewName);
		logger.debug("viewName: " + viewName);
		
		List<QnaVO> userListQna = null;
		
		userListQna = qnaService.userListQna(userId);
		
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("userListQna",userListQna);
		
		return mav;
	}
	
	/** 호준 - 유저 qna 글쓰기 창 보내기 **/
	@RequestMapping(value = "/user/userWriteForm")
	public ModelAndView userWriteForm(HttpServletRequest request){
		
		String viewName = getViewName(request);
		
		logger.info("viewName: " + viewName);
		logger.debug("viewName: " + viewName);
		
		ModelAndView mav = new ModelAndView(viewName);
		
		return mav;
	}
	
	/** 호준 - 유저 qna 글 작성 추가 후 목록으로 보내기  **/
	@RequestMapping(value = "/user/userWriteQna")
	public String userWriteQna(@ModelAttribute("qna") QnaVO qnaVO,HttpServletResponse response) throws IOException{
		System.out.println("컨트롤러" + qnaVO);
		String userId = qnaVO.getUserId();
		
		logger.info("userWriteQna 메서드 호출 " );
		logger.info(qnaVO.toString() );
		
		qnaService.userWriteQna(qnaVO);
		

		return "redirect:/qna/user/userListQna?userId="+userId;
	}
	
	/** 호준 - 유저 qna 상세보기 페이지 넘기기 **/
	@RequestMapping(value = "/user/userDetailQna")
	public ModelAndView userDetailQna(@ModelAttribute("qna") QnaVO qnaVO,HttpServletRequest request){
		
		String viewName = getViewName(request);
		logger.info("viewName: " + viewName);
		logger.debug("viewName: " + viewName);
		
		QnaVO userDetail = qnaService.userDetailQna(qnaVO);
		QnaVO DetailContent = qnaService.DetailContent(qnaVO);
		
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("userDetail",userDetail);
		mav.addObject("DetailContent",DetailContent);
		return mav;
	}
	
	/** 호준 - 유저 qna 글 수정하기 페이지 넘기기 **/
	@RequestMapping(value = "/user/userModifyQnaFrom")
	public ModelAndView userModifyQnaFrom(@ModelAttribute("qna") QnaVO qnaVO,HttpServletRequest request){
		String viewName = getViewName(request);
		
		logger.info("viewName: " + viewName);
		logger.debug("viewName: " + viewName);
		
		QnaVO userDetail = qnaService.userDetailQna(qnaVO);
		
		ModelAndView mav = new ModelAndView(viewName);
		
		mav.addObject("userDetail",userDetail);
		
		return mav;
	}
	
	private String getViewName(HttpServletRequest request) {
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
