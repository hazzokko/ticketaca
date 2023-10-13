package com.ticketaca.sts.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;

import org.springframework.stereotype.Component;

/**
 * 전시 정보 관련 VO
 * **/
@Component
public class ExhVO {

	private int exhNo = 1;					// 전시번호 (exh_no), 기본키
	private String exhName = "";			// 전시명 (exh_name)
	private String exhPlace = "";			// 전시관 이름 (exh_place)
	private String exhAddress = "";		// 전시관 주소 (exh_address)
	private String exhStrDate;				// 시작일자 (exh_strDate)
	private String exhEndDate;				// 종료일자 (exh_endDate)
	private int exhAPrice = 0;			// 성인 가격 (exh_aPrice)
	private int exhYPrice = 0;			// 청소년 가격 (exh_yPrice)
	private int exhCPrice = 0;			// 어린이 가격 (exh_cPrice)
	private String exhContent = "";		// 전시 내용 (exh_content)
	private int exhCrrCnt = 0;				// 현재 수량 (exh_crrCnt)
	private int exhMaxCnt = 0;				// 최대 수량 (exh_maxCnt)
	private String exhAbleBuy = "";		// 전시 상태 (exh_ableBuy)
	private String exhPostStatus = "";		// 게시 여부 (exh_postStatus)
	private String exhThumbImg = "";			// 메인 이미지 (exh_thumbImg)
	private String exhDetailImg = "";	// 상세 이미지 (exh_detailImg)
	
	public ExhVO() {
	}

	/* 전체 변수 사용하는 생성자 (전시 목록 출력, 전시 등록, 전시 수정에 사용) */
	public ExhVO(int exhNo, String exhName, String exhPlace, String exhAddress, String exhStrDate, String exhEndDate,
			int exhAPrice, int exhYPrice, int exhCPrice, String exhContent, int exhCrrCnt, int exhMaxCnt,
			String exhAbleBuy, String exhPostStatus, String exhThumbImg, String exhDetailImg) {
		super();
		this.exhNo = exhNo;
		this.exhName = exhName;
		this.exhPlace = exhPlace;
		this.exhAddress = exhAddress;
		this.exhStrDate = exhStrDate;
		this.exhEndDate = exhEndDate;
		this.exhAPrice = exhAPrice;
		this.exhYPrice = exhYPrice;
		this.exhCPrice = exhCPrice;
		this.exhContent = exhContent;
		this.exhCrrCnt = exhCrrCnt;
		this.exhMaxCnt = exhMaxCnt;
		this.exhAbleBuy = exhAbleBuy;
		this.exhPostStatus = exhPostStatus;
		this.exhThumbImg = exhThumbImg;
		this.exhDetailImg = exhDetailImg;
	}

	/* getter, setter */
	public int getExhNo() {
		return exhNo;
	}

	public void setExhNo(int exhNo) {
		this.exhNo = exhNo;
	}

	public String getExhName() {
		return exhName;
	}

	public void setExhName(String exhName) {
		this.exhName = exhName;
	}

	public String getExhPlace() {
		return exhPlace;
	}

	public void setExhPlace(String exhPlace) {
		this.exhPlace = exhPlace;
	}

	public String getExhAddress() {
		return exhAddress;
	}

	public void setExhAddress(String exhAddress) {
		this.exhAddress = exhAddress;
	}

	public String getExhStrDate() {
		return exhStrDate;
	}

	public void setExhStrDate(String exhStrDate) {
		this.exhStrDate = exhStrDate;
	}

	public String getExhEndDate() {
		return exhEndDate;
	}

	public void setExhEndDate(String exhEndDate) {
		this.exhEndDate = exhEndDate;
	}

	public int getExhAPrice() {
		return exhAPrice;
	}

	public void setExhAPrice(int exhAPrice) {
		this.exhAPrice = exhAPrice;
	}

	public int getExhYPrice() {
		return exhYPrice;
	}

	public void setExhYPrice(int exhYPrice) {
		this.exhYPrice = exhYPrice;
	}

	public int getExhCPrice() {
		return exhCPrice;
	}

	public void setExhCPrice(int exhCPrice) {
		this.exhCPrice = exhCPrice;
	}

	public String getExhContent() {
		return exhContent;
	}

	public void setExhContent(String exhContent) {
		this.exhContent = exhContent;
	}

	public int getExhCrrCnt() {
		return exhCrrCnt;
	}

	public void setExhCrrCnt(int exhCrrCnt) {
		this.exhCrrCnt = exhCrrCnt;
	}

	public int getExhMaxCnt() {
		return exhMaxCnt;
	}

	public void setExhMaxCnt(int exhMaxCnt) {
		this.exhMaxCnt = exhMaxCnt;
	}

	public String getExhAbleBuy() {
		return exhAbleBuy;
	}

	public void setExhAbleBuy(String exhAbleBuy) {
		this.exhAbleBuy = exhAbleBuy;
	}

	public String getExhPostStatus() {
		return exhPostStatus;
	}

	public void setExhPostStatus(String exhPostStatus) {
		this.exhPostStatus = exhPostStatus;
	}

	public String getExhThumbImg() {
		return exhThumbImg;
	}

	public void setExhThumbImg(String exhThumbImg) {
		this.exhThumbImg = exhThumbImg;
	}

	public String getExhDetailImg() {
		return exhDetailImg;
	}

	public void setExhDetailImg(String exhDetailImg) {
		this.exhDetailImg = exhDetailImg;
	}

	@Override
	public String toString() {
		return "ExhVO [exhNo=" + exhNo + ", exhName=" + exhName + ", exhPlace=" + exhPlace + ", exhAddress="
				+ exhAddress + ", exhStrDate=" + exhStrDate + ", exhEndDate=" + exhEndDate + ", exhAPrice=" + exhAPrice
				+ ", exhYPrice=" + exhYPrice + ", exhCPrice=" + exhCPrice + ", exhContent=" + exhContent
				+ ", exhCrrCnt=" + exhCrrCnt + ", exhMaxCnt=" + exhMaxCnt + ", exhAbleBuy=" + exhAbleBuy
				+ ", exhPostStatus=" + exhPostStatus + ", exhThumbImg=" + exhThumbImg + ", exhDetailImg=" + exhDetailImg
				+ "]";
	}
}
