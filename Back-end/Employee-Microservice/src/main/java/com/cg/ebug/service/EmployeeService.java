package com.cg.ebug.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ebug.dao.CriticalLevel_Repository;
import com.cg.ebug.dao.Employee_Repository;
import com.cg.ebug.dao.Status_Repository;
import com.cg.ebug.dao.Ticket_Repository;
import com.cg.ebug.entity.CriticalLevel_Table;
import com.cg.ebug.entity.Employee_Table;
import com.cg.ebug.entity.Status_Table;
import com.cg.ebug.entity.Ticket_Table;
import com.cg.ebug.exception.TicketNotFoundException;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private Ticket_Repository ticketRepository;
	@Autowired
	private Status_Repository statusRepository;
	@Autowired
	private CriticalLevel_Repository criticalRepository;
	@Autowired
	private Employee_Repository employeeRepository;

	public Status_Table addStatus(Status_Table status) {

		return statusRepository.save(status);
	}

	public CriticalLevel_Table addCriticalLevel(CriticalLevel_Table critical) {

		return criticalRepository.save(critical);
	}

	public Employee_Table login(String emailId, String password) {

		return employeeRepository.login(emailId, password);
	}

	public List<Ticket_Table> getTicketByStatus(Long statusId, Long employeeId) {
		List<Ticket_Table> ticketList = ticketRepository.FindTicketByStatusIdByEmp(statusId, employeeId);
		if (ticketList.size() == 0) {
			throw new TicketNotFoundException("There is no ticket available for the status id: " + statusId, "400");
		} else {
			return ticketList;
		}
	}

	public List<Ticket_Table> getTicketByCriticalLevelId(Long criticalId, Long employeeId) {
		List<Ticket_Table> ticketList = ticketRepository.FindTicketByCriticalLevelIdByEmp(criticalId, employeeId);
		if (ticketList.size() == 0) {
			throw new TicketNotFoundException("There is no ticket available for the critical level id: " + criticalId,
					"200");
		} else {
			return ticketList;
		}
	}

	public List<Ticket_Table> getTicketByProjectId(Long projectId, Long employeeId) {
		List<Ticket_Table> ticketList = ticketRepository.FindTicketByProjectIdByEmp(projectId, employeeId);
		if (ticketList.size() == 0) {
			throw new TicketNotFoundException("There is no ticket available for the project id: " + projectId, "401");
		} else {
			return ticketList;
		}
	}

	public String sendSolutionToCustomer(String solution) {

		return null;
	}

	public String assignTaskToOtherEmployee(Long employeeId) {

		return null;
	}

}
