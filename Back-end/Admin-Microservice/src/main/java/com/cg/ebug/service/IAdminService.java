package com.cg.ebug.service;

import java.util.List;


import com.cg.ebug.entity.Employee_Table;
import com.cg.ebug.entity.Project_Table;
import com.cg.ebug.entity.Ticket_Table;

public interface IAdminService {

	List<Ticket_Table> getAllTickets();

	Ticket_Table getTicketById(Long id);

//	Ticket_Table createTicket(Ticket_Table ticket);
	Ticket_Table updateTicketByAdmin(Ticket_Table ticket);

	Project_Table addProject(Project_Table project);

	List<Project_Table> getAllProject();

	Project_Table getProjectByID(Long id);

	

	Ticket_Table assignTicketToEmployeeByAdmin(Ticket_Table newTicket);

	Employee_Table registerEmployee(Employee_Table customer);

	long countProject();

	long countEmployee();

	long countPending();

	List<Ticket_Table> updateticketList();

}
