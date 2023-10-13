package com.ticketaca.sts.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.ticketaca.sts.vo.AdminVO;

public interface AdminController {
	public ModelAndView adminLoginForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView adminLogin(@ModelAttribute("admin") AdminVO adminVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView adminLogout(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView adminUserList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView adminUserSearch(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
