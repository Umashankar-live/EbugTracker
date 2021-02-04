package com.cg.ebug.service;

import java.util.List;


import com.cg.ebug.entity.EmployeeTable;
import com.cg.ebug.entity.ProjectTable;
import com.cg.ebug.entity.TicketTable;

public interface IAdminService {

	List<TicketTable> getAllTickets();

	TicketTable getTicketById(Long id);

	TicketTable updateTicketByAdmin(TicketTable ticket);

	ProjectTable addProject(ProjectTable project);

	List<ProjectTable> getAllProject();

	ProjectTable getProjectByID(Long id);

	

	TicketTable assignTicketToEmployeeByAdmin(TicketTable newTicket);

	EmployeeTable registerEmployee(EmployeeTable customer);

	long countProject();

	long countEmployee();

	long countPending();

	List<TicketTable> updateticketList();

}
