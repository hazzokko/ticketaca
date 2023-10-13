package com.ticketaca.sts.vo;

import org.springframework.stereotype.Component;

@Component
public class AdminVO {
	private String admId;
	private String admPw;
	private String admNick;
	public String getAdmId() {
		return admId;
	}
	public void setAdmId(String admId) {
		this.admId = admId;
	}
	public String getAdmPw() {
		return admPw;
	}
	public void setAdmPw(String admPw) {
		this.admPw = admPw;
	}
	public String getAdmNick() {
		return admNick;
	}
	public void setAdmNick(String admNick) {
		this.admNick = admNick;
	}
	
	@Override
	public String toString() {
		return "AdminVO [admId=" + admId + ", admPw=" + admPw + ", admNick=" + admNick + "]";
	}

	
	
	
}
