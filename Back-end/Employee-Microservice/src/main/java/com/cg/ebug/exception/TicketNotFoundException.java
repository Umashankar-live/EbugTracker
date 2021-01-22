package com.cg.ebug.exception;

public class TicketNotFoundException extends RuntimeException {
	private String msg;
	private String statusCode;

	public TicketNotFoundException(String msg, String statusCode) {
		super();
		this.msg = msg;
		this.statusCode = statusCode;
	}

	TicketNotFoundException() {
		super();
	}
}
