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

import com.ticketaca.sts.service.AdminService;
import com.ticketaca.sts.vo.AdminVO;
import com.ticketaca.sts.vo.ExhVO;
import com.ticketaca.sts.vo.UserVO;

@Controller("adminController")
@RequestMapping("/admin")
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class AdminControllerImpl implements AdminController {
	private static final long serialVersionUID = 1L;
	private static String exhImageRepo = "C:\\ticket\\exh_image";
	private static final Logger logger = LoggerFactory.getLogger(AdminControllerImpl.class);
	
	
	@Autowired
	AdminService adminService;
	@Autowired
	ExhVO exhVO;
	private HttpSession session;
	
	@RequestMapping(value="/loginForm", method= RequestMethod.GET)
	public ModelAndView adminLoginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		logger.info("viewName : " + viewName);
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
		
	}
	@RequestMapping(value="/login", method= RequestMethod.POST)
	public ModelAndView adminLogin(@ModelAttribute("admin") AdminVO adminVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		logger.info("viewName : " + viewName);
		AdminVO adminInfo = adminService.adminLogin(adminVO);
		if(adminInfo == null){
			request.setAttribute("msg", "loginFail");
			ModelAndView mav = new ModelAndView("/admin/loginForm");
			return mav;
		}else{
			session = request.getSession();
			session.setAttribute("adm", adminInfo);
			request.setAttribute("msg", "loginSuccess");
			
			ModelAndView mav = new ModelAndView("/admin/main");
			return mav;
		}
	}
	
	/** jcm / admin : 로그아웃**/
	@RequestMapping(value="/logout", method= RequestMethod.GET)
	public ModelAndView adminLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		logger.info("viewName : " + viewName);
		
		session.invalidate();
		request.setAttribute("msg", "logout");
		ModelAndView mav = new ModelAndView("/admin/main");
		return mav;
		}
	
	/** jcm / admin : 로그아웃하고 유저페이지로 이동**/
	@RequestMapping(value="/logout/to", method= RequestMethod.GET)
	public ModelAndView adminLogoutTo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		logger.info("viewName : " + viewName);
		
		session.invalidate();
		request.setAttribute("msg", "logout");
		ModelAndView mav = new ModelAndView("main");
		return mav;
	}
	
	/** jcm / admin : 유저관리**/
	@RequestMapping(value="/user/list", method= RequestMethod.GET)
	public ModelAndView adminUserList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		logger.info("viewName : " + viewName);
		session = request.getSession();
		if(session.getAttribute("adm") == null){
			ModelAndView mav = new ModelAndView("/admin/main");
			return mav;
		};
		List<UserVO> userList = adminService.adminUserList();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("userList", userList);
		return mav;
		}
	
	/** jcm / admin : 유저관리 - 유저검색 **/
	@RequestMapping(value="/user/search", method= RequestMethod.POST)
	public ModelAndView adminUserSearch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		logger.info("viewName : " + viewName);
		String condition = request.getParameter("condition");
		String keyword = request.getParameter("keyword");
		
		List<UserVO> searchList = adminService.adminUserSearch(condition, keyword);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("searchList", searchList);
		return mav;
		}
	
	
	/** 뷰네임 **/
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
