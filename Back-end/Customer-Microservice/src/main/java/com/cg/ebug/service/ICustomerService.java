package com.cg.ebug.service;

import java.util.List;

import com.cg.ebug.entity.Ticket_Table;

public interface ICustomerService {
	
	
	    Ticket_Table viewTicketById(Long id);
		
	    Ticket_Table createTicket(Ticket_Table ticket);
		
	    List<Ticket_Table> getTicketByStatusByCus(Long statusId, Long employeeId);
	    
		List<Ticket_Table> getTicketByCriticalLevelIdByCus(Long criticalId, Long employeeId);

}
