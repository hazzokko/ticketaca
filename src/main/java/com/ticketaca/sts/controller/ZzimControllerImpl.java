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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ticketaca.sts.service.ZzimService;
import com.ticketaca.sts.vo.UserVO;
import com.ticketaca.sts.vo.ZzimVO;

@Controller("zzimController")
@RequestMapping("/zzim")
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class ZzimControllerImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(ZzimControllerImpl.class);
	
	@Autowired
	private ZzimService zzimService;
	@Autowired
	private ZzimVO zzimVO;
	
	@RequestMapping(value="/list", method= RequestMethod.GET)
	public ModelAndView zzimList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		logger.info(viewName);
		
		HttpSession session = request.getSession();
		UserVO userInfo =  (UserVO) session.getAttribute("user");
		List<ZzimVO> zzimList = zzimService.zzimList(userInfo.getUserId());
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("zzimList", zzimList);
		return mav;
	}	
	
	@ResponseBody
	@RequestMapping(value="/remove/{zzNo}", method= RequestMethod.GET)
	public String zzimRemove(@PathVariable("zzNo") int zzNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		logger.info(viewName);
		boolean result = zzimService.zzimRemove(zzNo);
		if(result == true){
			return "success";
		}else{
			return "fail";
		}
		
	}
	
	
	
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
