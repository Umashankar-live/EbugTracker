package com.cg.ebug.service;

import java.util.List;

import com.cg.ebug.entity.CriticalLevel_Table;
import com.cg.ebug.entity.Employee_Table;
import com.cg.ebug.entity.Status_Table;
import com.cg.ebug.entity.Ticket_Table;

public interface IEmplyeeService {
	
    Status_Table addStatus(Status_Table status);
    
    CriticalLevel_Table addCriticalLevel(CriticalLevel_Table critical);
    
    Employee_Table login(String emailId, String password);
    
    List<Ticket_Table> getTicketByStatus(Long statusId, Long employeeId);
    
	List<Ticket_Table> getTicketByCriticalLevelId(Long criticalId, Long employeeId);

	List<Ticket_Table> getTicketByProjectId(Long projectId, Long employeeId);
	
	String sendSolutionToCustomer(String solution);
	
	String assignTaskToOtherEmployee(Long employeeId);

}
