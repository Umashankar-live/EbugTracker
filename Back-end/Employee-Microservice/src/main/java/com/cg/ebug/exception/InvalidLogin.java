package com.cg.ebug.exception;

public class InvalidLogin extends RuntimeException {
	private String msg;
	private String statusCode;
	
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public InvalidLogin(String msg, String statusCode) {
		super();
		this.msg = msg;
		this.statusCode = statusCode;
	}

	public InvalidLogin() {
		super();
	}
}
