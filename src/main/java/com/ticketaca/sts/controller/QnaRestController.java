package com.ticketaca.sts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ticketaca.sts.service.QnaService;
import com.ticketaca.sts.vo.QnaVO;

@RestController
@RequestMapping("/qna")
public class QnaRestController {
	static Logger logger = LoggerFactory.getLogger(QnaRestController.class);

	@Autowired
	private QnaVO qnaVO;

	@Autowired
	private QnaService qnaService;

	public QnaRestController() {
		System.out.println("QnaRestController()");
	}

	/** 호준 - 관리자 qna 답글 작성 **/
	@RequestMapping(value = "/admin/adminWriteQna", method = RequestMethod.POST)
	public ResponseEntity<String> adminWriteQna(@RequestBody QnaVO qnaVO) {

		ResponseEntity<String> resEntity = null;

		try {
			logger.info("adminWriteQna 메서드 호출");
			logger.info(qnaVO.toString());

			int qnaNo = qnaVO.getQnaNo();

			qnaService.adminWriteQna(qnaVO);
			qnaService.adminUpdateRead(qnaNo);

			resEntity = new ResponseEntity("처리되었습니다", HttpStatus.OK);
		} catch (Exception e) {
			resEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return resEntity;
	}

	/** 호준 - 유저 qna 질문 수정 **/
	@RequestMapping(value = "/user/userModifyQna", method = RequestMethod.PUT)
	public ResponseEntity<?> userModfiyQna(@RequestBody QnaVO qnaVO) {
		
		ResponseEntity<String> resEntity = null;
		
		try {
			logger.info("userModfiyQna 메서드 호출");
			logger.info(qnaVO.toString());
			
			qnaService.userModfiyQna(qnaVO);
			
			resEntity = new ResponseEntity("asdfasdf", HttpStatus.OK);
		} catch (Exception e) {
			resEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}	
}
