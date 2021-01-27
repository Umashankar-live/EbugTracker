package com.cg.ebug.service;

import java.util.List;

import com.cg.ebug.entity.Employee_Table;
import com.cg.ebug.entity.Ticket_Table;

public interface IEmployeeService {

	Ticket_Table getTicketById(Long ticketId);

	Employee_Table getEmpList(Long empId);

	Ticket_Table assignTicketToEmployeeByEmployee(Ticket_Table newTicket);


	Ticket_Table updateTicketByEmployeeForSolution(Ticket_Table newTicket);

	List<Ticket_Table> getTicketList(Long empId);
	
    
    
    

}
