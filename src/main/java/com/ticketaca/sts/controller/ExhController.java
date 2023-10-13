package com.ticketaca.sts.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ticketaca.sts.file.FileUploadService;
import com.ticketaca.sts.service.ExhService;
import com.ticketaca.sts.vo.ExhVO;

@Controller("exhController")
public class ExhController {
	private static final Logger logger = LoggerFactory.getLogger(ExhController.class);
	
	@Autowired
	private ExhService exhService; // 타입이 ExhService라 해당 인터페이스에 메서드가 구현되어있어야함
	@Resource
	FileUploadService fileUploadService;
	@Autowired
	private ExhVO exhVO;
	
	// ------------------------- admin -------------------------
	/* 전체 목록 출력 */
	@RequestMapping(value="/admin/listExhs", method=RequestMethod.GET)
	public String selectAllExhListAdmin(Model model) throws Exception {
		List<ExhVO> exhsList = exhService.selectAllExhList();
		model.addAttribute("exhsList", exhsList);
		return "/admin/listExhs";
	}
	
	/* 종료 전시 목록 출력 */
	@RequestMapping(value="/admin/listExhsClosing", method=RequestMethod.GET)
	public String selectExhListClosingAdmin(Model model) throws Exception {
		List<ExhVO> exhsList = exhService.selectExhListClosing();
		model.addAttribute("exhsList", exhsList);
		return "/admin/listExhsClosing";
	}
	
	/* 종료 예정 전시 목록 출력 */
	@RequestMapping(value="/admin/listExhsNearingDeadline", method=RequestMethod.GET)
	public String selectExhListNearingDeadlineAdmin(Model model) throws Exception {
		List<ExhVO> exhsList = exhService.selectExhListNearingDeadline();
		model.addAttribute("exhsList", exhsList);
		return "/admin/listExhsNearingDeadline";
	}
	
	/* 진행 중 전시 목록 출력 */
	@RequestMapping(value="/admin/listExhsOngoing", method=RequestMethod.GET)
	public String selectExhListOngoingAdmin(Model model) throws Exception {
		List<ExhVO> exhsList = exhService.selectExhListOngoing();
		model.addAttribute("exhsList", exhsList);
		return "/admin/listExhsOngoing";
	}
	
	/* 예정 전시 목록 출력 */
	@RequestMapping(value="/admin/listExhsUpcoming", method=RequestMethod.GET)
	public String selectExhListUpcomingAdmin(Model model) throws Exception {
		List<ExhVO> exhsList = exhService.selectExhListUpcoming();
		model.addAttribute("exhsList", exhsList);
		return "/admin/listExhsUpcoming";
	}
	
	/* 전시 상세 정보 출력 : 이미지를 @PathVariable로 받아오면서 원래 구현한 부분 주석처리  */
	@RequestMapping(value="/admin/detailExh", method=RequestMethod.GET)
	public String selectOneExhAdmin(@RequestParam int exhNo, @RequestParam String exhThumbImg, Model model) throws Exception {
		ExhVO exhVO = exhService.selectOneExh(exhNo);
		model.addAttribute("exhVO", exhVO);
		return "/admin/detailExh";
	}
	
	/* 전시 상세 정보 출력 
	// 1. 브라우저에서 요청 시 {num} 부분의 값이 @PathVariable로 지정
	@RequestMapping(value={"/admin/detailExh/{exhNo}/{exhThumbImg}"}, method=RequestMethod.GET)
	// 2. 요청 URL에서 지정된 값이 num에 자동으로 할당
	public String selectOneExhAdmin(@PathVariable int exhNo, @PathVariable String exhThumbImg, Model model) throws Exception {
		ExhVO exhVO = exhService.selectOneExh(exhNo);
		model.addAttribute("exhVO", exhVO);
		return "/admin/detailExh";
	}*/
	
	/* 전시 번호를 가지고 신규 전시 등록 폼으로 이동 */
	@RequestMapping(value="/admin/insertExh", method=RequestMethod.GET)
	public String updateExhForm(Model model) throws Exception {
		model.addAttribute("exhNo", exhService.findMaxExhNo() + 1);
		return "/admin/insertExh";
	}
	
	/* 신규 전시 등록 */
	@RequestMapping(value="/admin/insertExh", method=RequestMethod.POST)
	public String insertExh(@ModelAttribute ExhVO exhVO
							, MultipartHttpServletRequest multipartRequest) throws Exception {
		try {
			fileUploadService.upload(exhVO.getExhNo(), multipartRequest);
			exhService.insertExh(exhVO);
		} catch (Exception e) {
			if(logger.isErrorEnabled()) {
				logger.error("#Exception Message : {}", e.getMessage());
			}
		}
		return "redirect:/admin/listExhs";
	}
	
	/* 전시 번호를 가지고 정보 수정창으로 이동 */
	@RequestMapping(value="/admin/updateExh/{exhNo}", method=RequestMethod.GET)
	public String updateExhForm(@PathVariable int exhNo, Model model) throws Exception {
		System.out.println("/admin/updateExh/{exhNo}");
		System.out.println(exhNo+"<++++++++++++++++++++++++++++++++");
		model.addAttribute("exhNo", exhNo);
		ExhVO exhVO = exhService.selectOneExh(exhNo);
			
		model.addAttribute("exhVO", exhVO);
		return "/admin/updateExh";
	}
	
	/* 전시 정보 수정 */
	@RequestMapping(value="/admin/updateExh", method=RequestMethod.POST)
	public String updateExh(@ModelAttribute ExhVO exhVO) throws Exception {
		System.out.println("/admin/updateExh");
		System.out.println(exhVO+"<++++++++++++++++++++++++++++++++");
		exhService.updateExh(exhVO);
		return "redirect:/admin/listExhs";
	}
		
	/* 전시 삭제 */
	@RequestMapping(value="/admin/deleteExh/{exhNo}", method=RequestMethod.GET)
	public String deleteExh(@PathVariable int exhNo) throws Exception {
		exhService.deleteExh(exhNo);
		return "redirect:/admin/listExhs"; // @@@@@@@@@@@@
	}
	
	// ------------------------- user -------------------------
	/* 전체 목록 출력 */
	@RequestMapping(value="/user/listExhs", method=RequestMethod.GET)
	public String selectAllExhListUser(Model model) throws Exception {
		List<ExhVO> exhsList = exhService.selectAllExhList();
		model.addAttribute("exhsList", exhsList);
		return "/user/listExhs";
	}
	
	/* 종료 전시 목록 출력 */
	@RequestMapping(value="/user/listExhsClosing", method=RequestMethod.GET)
	public String selectExhListClosingUser(Model model) throws Exception {
		List<ExhVO> exhsList = exhService.selectExhListClosing();
		model.addAttribute("exhsList", exhsList);
		return "/user/listExhsClosing";
	}
	
	/* 종료 예정 전시 목록 출력 */
	@RequestMapping(value="/user/listExhsNearingDeadline", method=RequestMethod.GET)
	public String selectExhListNearingDeadlineUser(Model model) throws Exception {
		List<ExhVO> exhsList = exhService.selectExhListNearingDeadline();
		model.addAttribute("exhsList", exhsList);
		return "/user/listExhsNearingDeadline";
	}
	
	/* 진행 중 전시 목록 출력 */
	@RequestMapping(value="/user/listExhsOngoing", method=RequestMethod.GET)
	public String selectExhListOngoingUser(Model model) throws Exception {
		List<ExhVO> exhsList = exhService.selectExhListOngoing();
		model.addAttribute("exhsList", exhsList);
		return "/user/listExhsOngoing";
	}
	
	/* 예정 전시 목록 출력 */
	@RequestMapping(value="/user/listExhsUpcoming", method=RequestMethod.GET)
	public String selectExhListUpcomingUser(Model model) throws Exception {
		List<ExhVO> exhsList = exhService.selectExhListUpcoming();
		model.addAttribute("exhsList", exhsList);
		return "/user/listExhsUpcoming";
	}
	
	/* 전시 상세 정보 출력 : 이미지를 @PathVariable로 받아오면서 원래 구현한 부분 주석처리  */
	@RequestMapping(value="/user/detailExh", method=RequestMethod.GET)
	public String selectOneExhUser(@RequestParam int exhNo, @RequestParam String exhThumbImg, Model model) throws Exception {
		ExhVO exhVO = exhService.selectOneExh(exhNo);
		model.addAttribute("exhVO", exhVO);
		return "/user/detailExh";
	}
	
	/* 전시 상세 정보 출력 
	// 1. 브라우저에서 요청 시 {num} 부분의 값이 @PathVariable로 지정
	@RequestMapping(value={"/user/detailExh/{exhNo}/{exhThumbImg}"}, method=RequestMethod.GET)
	// 2. 요청 URL에서 지정된 값이 num에 자동으로 할당
	public String selectOneExhUser(@PathVariable int exhNo, @PathVariable String exhThumbImg, Model model) throws Exception {
		ExhVO exhVO = exhService.selectOneExh(exhNo);
		model.addAttribute("exhVO", exhVO);
		return "/user/detailExh";
	}*/
}
