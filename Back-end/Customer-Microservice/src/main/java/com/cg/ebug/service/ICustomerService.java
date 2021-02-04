package com.cg.ebug.service;

import java.util.List;

import com.cg.ebug.entity.TicketTable;

public interface ICustomerService {

	TicketTable viewTicketById(Long id);

	TicketTable updateTicketByCustomer(TicketTable ticket);

	List<TicketTable> getAllTicketsByCustomerId(long custId);

}
