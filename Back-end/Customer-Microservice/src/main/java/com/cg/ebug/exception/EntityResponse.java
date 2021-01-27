package com.cg.ebug.exception;

import java.util.Date;
import java.util.List;


import com.cg.ebug.entity.Ticket_Table;

public class EntityResponse {

	private Date timeStamp;
	private String message;
	private String details;
	
	private boolean error;
	
	
	private Ticket_Table ticketTable;
	private List<Ticket_Table> listOfTickets;
	
	public EntityResponse(Date timeStamp, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}
	
	

	public EntityResponse() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	


	public Ticket_Table getTicketTable() {
		return ticketTable;
	}



	public void setTicketTable(Ticket_Table ticketTable) {
		this.ticketTable = ticketTable;
	}



	public List<Ticket_Table> getListOfTickets() {
		return listOfTickets;
	}



	public void setListOfTickets(List<Ticket_Table> listOfTickets) {
		this.listOfTickets = listOfTickets;
	}
	
	
	
}