package com.cg.ebug.service;

import java.util.List;

import com.cg.ebug.entity.CriticalLevel_Table;
import com.cg.ebug.entity.Employee_Table;
import com.cg.ebug.entity.Project_Table;
import com.cg.ebug.entity.Status_Table;
import com.cg.ebug.entity.Ticket_Table;

public interface IAdminService {

	 List<Ticket_Table> getAllTickets();
	 Ticket_Table getTicketById(Long id);
//	 Ticket_Table createTicket(Ticket_Table ticket);
	 Ticket_Table updateTicketByAdmin(Ticket_Table ticket);
     List<Status_Table> getAllStatus() ;

     List<CriticalLevel_Table> getAllCritical();
     
     Project_Table addProject(Project_Table project);
     List<Project_Table> getAllProject();
     Project_Table getProjectByID(Long id);
	List<Ticket_Table> getTicketByStatusId(Long id);
	List<Ticket_Table> getTicketByCriticalLevelId(Long id);
	List<Ticket_Table> getTicketByProjectId(Long id);
	
	 Ticket_Table assignTicketToEmployeeByAdmin(Long ticketId, Long employeeId);
	 
	 Ticket_Table updateTicketStatusById(Long id);
	 
	 Employee_Table registerEmployee(Employee_Table customer);
     

}
