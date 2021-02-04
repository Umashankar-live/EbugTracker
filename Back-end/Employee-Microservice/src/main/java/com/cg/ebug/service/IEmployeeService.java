package com.cg.ebug.service;

import java.util.List;

import com.cg.ebug.entity.EmployeeTable;
import com.cg.ebug.entity.TicketTable;

public interface IEmployeeService {

	TicketTable getTicketById(Long ticketId);

	EmployeeTable getEmpList(Long empId);

	TicketTable assignTicketToEmployeeByEmployee(TicketTable newTicket);


	TicketTable updateTicketByEmployeeForSolution(TicketTable newTicket);

	List<TicketTable> getTicketList(Long empId);
	
    
    
    

}
