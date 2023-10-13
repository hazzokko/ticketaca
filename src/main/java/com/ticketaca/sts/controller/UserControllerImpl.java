package com.ticketaca.sts.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ticketaca.sts.service.UserService;
import com.ticketaca.sts.vo.UserVO;

@Controller("userController")
@RequestMapping("/user")
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class UserControllerImpl implements UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserControllerImpl.class);
	
	@Autowired
	UserService userService;
	private HttpSession session;
	
	/** jcm - 로그인 버튼 클릭 **/
	@RequestMapping(value="/login", method= RequestMethod.POST)
	public ModelAndView userLogin(@ModelAttribute("user") UserVO userVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		logger.info("viewName : " + viewName);
		UserVO userInfo = userService.userLogin(userVO);
		if(userInfo == null){
			request.setAttribute("msg", "loginFail");
			ModelAndView mav = new ModelAndView("/user/loginForm");
			return mav;
		}else if (userInfo.getUserOut().equals("T")) {
			request.setAttribute("msg", "userOut");
			ModelAndView mav = new ModelAndView("main");
			return mav;
		}else{
			session = request.getSession();
			session.setAttribute("user", userInfo);
			request.setAttribute("msg", "loginSuccess");
			
			ModelAndView mav = new ModelAndView("main");
			return mav;
		}
	}
	
	
	/** jcm - 로그인 폼으로 이동 **/
	@RequestMapping(value="/loginForm", method= RequestMethod.GET)
	public ModelAndView userLoginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		logger.info("viewName : " + viewName);
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}
	
	/** jcm - 로그아웃 후 메인으로 이동**/
	@RequestMapping(value="/logout", method= RequestMethod.GET)
	public ModelAndView userLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		logger.info("viewName : " + viewName);
		session = request.getSession();
		session.invalidate();
		request.setAttribute("msg", "logout");
		ModelAndView mav = new ModelAndView("main");
		return mav;
	}
	
	/** jcm - 로그아웃 후 어드민 페이지로 이동**/
	@RequestMapping(value="/logout/to", method= RequestMethod.GET)
	public ModelAndView userLogoutTo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		logger.info("viewName : " + viewName);
		session = request.getSession();
		session.invalidate();
		request.setAttribute("msg", "logout");
		ModelAndView mav = new ModelAndView("/admin/main");
		return mav;
	}
	
	/** jcm - 회원가입 폼으로 이동 **/
	@RequestMapping(value="/signForm", method= RequestMethod.GET)
	public ModelAndView userSignForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		logger.info("viewName : " + viewName);
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}
	
	/** jcm - 회원가입 후 메인으로 이동**/
	@RequestMapping(value="/signUp", method= RequestMethod.POST)
	public ModelAndView userSignUp(@ModelAttribute("user") UserVO userVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		logger.info("viewName : " + viewName);
		
		boolean result = userService.userSignUp(userVO);
		if(result == true){
			request.setAttribute("msg", "signUpUser");
		} else{			
			request.setAttribute("msg", "signUpFail");
		}
		ModelAndView mav = new ModelAndView("main");
		return mav;
	}
	
	
	/** jcm - 유저 마이페이지 이동 **/
	@RequestMapping(value="/myPage", method= RequestMethod.GET)
	public ModelAndView userMyPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		logger.info("viewName : " + viewName);
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}
	
	/** jcm - 유저 마이페이지 비밀번호 검증 후 수정페이지로 이동 **/
	@RequestMapping(value="/myPage/mod", method= RequestMethod.GET)
	public ModelAndView userMyPageMod(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		logger.info("viewName : " + viewName);
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}
	
	/** jcm - 유저 마이페이지 비밀번호 검증 후 수정페이지로 이동 **/
	@RequestMapping(value="/myPage/out", method= RequestMethod.GET)
	public ModelAndView userMyPageOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		logger.info("viewName : " + viewName);
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}
	
	/** jcm / user : 마이페이지 회원정보 수정 **/
	@RequestMapping(value = "/myPage/update", method = RequestMethod.POST)
	public ModelAndView userUpdate(@ModelAttribute("user") UserVO userVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName = getViewName(request);
		logger.info(viewName);
		UserVO userVO1 = userService.userUpdate(userVO);
		ModelAndView mav = new ModelAndView("/user/myPage/mod");
		mav.addObject("user", userVO1);
		mav.addObject("msg", "UpdateSuccess");
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
