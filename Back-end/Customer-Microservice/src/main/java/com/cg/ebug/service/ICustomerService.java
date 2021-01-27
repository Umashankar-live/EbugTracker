package com.cg.ebug.service;

import java.util.List;

import com.cg.ebug.entity.Ticket_Table;

public interface ICustomerService {

	Ticket_Table viewTicketById(Long id);

	Ticket_Table updateTicketByCustomer(Ticket_Table ticket);

	List<Ticket_Table> getAllTicketsByCustomerId(long custId);

}
