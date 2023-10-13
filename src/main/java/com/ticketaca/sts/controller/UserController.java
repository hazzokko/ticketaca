package com.ticketaca.sts.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.ticketaca.sts.vo.UserVO;

public interface UserController {
	public ModelAndView userLogin(@ModelAttribute("user") UserVO userVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView userLoginForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView userLogout(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView userSignForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView userSignUp(@ModelAttribute("user") UserVO userVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView userMyPage(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView userMyPageMod(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView userMyPageOut(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView userUpdate(@ModelAttribute("user") UserVO userVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
