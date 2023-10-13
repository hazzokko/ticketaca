package com.ticketaca.sts.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("QnaVO")
public class QnaVO {
	private int level;			//트리 구조 계층 번호
	private int qnaNo;			//글 번호
	private int qnaPNo;			//답변번호(정렬용)
	private String qnaCate;		//질문 카테고리
	private int exhNo;			//상품 번호
	private String qnaTitle;		//QnA 제목
	private Date qnaDate;			//문의 일자
	private String userId;			//회원 ID
	private String qnaContent;	//문의 내용
	private String userRead;		//유저가 읽었는지 확인
	private String admRead;		//관리자가 읽었는지 확인
	private String admAnswer;		//관리자 답변 여부
	
	public QnaVO() {
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getQnaNo() {
		return qnaNo;
	}

	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}

	public int getQnaPNo() {
		return qnaPNo;
	}

	public void setQnaPNo(int qnaPNo) {
		this.qnaPNo = qnaPNo;
	}

	public String getQnaCate() {
		return qnaCate;
	}

	public void setQnaCate(String qnaCate) {
		this.qnaCate = qnaCate;
	}

	public int getExhNo() {
		return exhNo;
	}

	public void setExhNo(int exhNo) {
		this.exhNo = exhNo;
	}

	public String getQnaTitle() {
		return qnaTitle;
	}

	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}

	public Date getQnaDate() {
		return qnaDate;
	}

	public void setQnaDate(Date qnaDate) {
		this.qnaDate = qnaDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getQnaContent() {
		return qnaContent;
	}

	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}

	public String getUserRead() {
		return userRead;
	}

	public void setUserRead(String userRead) {
		this.userRead = userRead;
	}

	public String getAdmRead() {
		return admRead;
	}

	public void setAdmRead(String admRead) {
		this.admRead = admRead;
	}

	public String getAdmAnswer() {
		return admAnswer;
	}

	public void setAdmAnswer(String admAnswer) {
		this.admAnswer = admAnswer;
	}

	@Override
	public String toString() {
		return "QnaVO [level=" + level + ", qnaNo=" + qnaNo + ", qnaPNo=" + qnaPNo + ", qnaCate=" + qnaCate + ", exhNo="
				+ exhNo + ", qnaTitle=" + qnaTitle + ", qnaDate=" + qnaDate + ", userId=" + userId + ", qnaContent="
				+ qnaContent + ", userRead=" + userRead + ", admRead=" + admRead + ", admAnswer=" + admAnswer + "]";
	}

	
	
	
}
