package com.ticketaca.sts.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ticketaca.sts.service.UserService;
import com.ticketaca.sts.vo.UserVO;

@RestController
@RequestMapping("/user/req")
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class UserRestControllerImpl implements UserRestController {

	private static final Logger logger = LoggerFactory.getLogger(UserRestControllerImpl.class);

	@Autowired
	UserService userService;

	/** jcm / user : 회원가입 id중복확인 **/
	@RequestMapping(value = "/checkId", method = RequestMethod.POST)
	public String userCheckId(@RequestBody UserVO userVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		boolean duplicatedId = userService.duplicatedId(userVO);
		String result = "error";

		if (duplicatedId == true) {
			result = "not_usable";
		} else {
			result = "usable";
		}
		return result;
	}
	
	/** jcm / user : 회원가입 id중복확인 **/
	@RequestMapping(value = "/out", method = RequestMethod.DELETE)
	public String userOut(@RequestBody UserVO userVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		boolean userOut = userService.userOut(userVO);
		String result = "success";
		
		if (userOut == true) {
			result = "success";
		} else {
			result = "fail";
		}
		return result;
	}
	
	
	
	
	
	
	
}
