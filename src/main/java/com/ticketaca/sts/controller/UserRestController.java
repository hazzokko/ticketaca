package com.ticketaca.sts.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;

import com.ticketaca.sts.vo.UserVO;

public interface UserRestController {
	public String userCheckId(@RequestBody UserVO userVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public String userOut(@RequestBody UserVO userVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	

}
