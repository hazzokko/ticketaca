package com.ticketaca.sts.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

public interface ZzimController {
	public ModelAndView zzimList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public String zzimRemove(@PathVariable("zzNo") int zzNo, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
