package com.cg.ebug.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ebug.dao.Employee_Repositroy;
import com.cg.ebug.dao.Project_Repository;
import com.cg.ebug.dao.Ticket_Repository;
import com.cg.ebug.entity.Employee_Table;
import com.cg.ebug.entity.Project_Table;
import com.cg.ebug.entity.Ticket_Table;
import com.cg.ebug.exception.EbugException;

@Service
@Transactional
public class AdminService implements IAdminService {

	@Autowired
	private Ticket_Repository ticketRepository;
	@Autowired
	private Project_Repository projectRepository;
	@Autowired
	private Employee_Repositroy employeeRepository;

	@Override
	public List<Ticket_Table> getAllTickets() {
		List<Ticket_Table> responseList = new ArrayList<Ticket_Table>();
		List<Ticket_Table> ticketList  = ticketRepository.findAllTicket();
		for (Ticket_Table tickets : ticketList) {
			Ticket_Table ticket = new Ticket_Table();
			ticket.setId(tickets.getId());
			ticket.setCustId(tickets.getCustId());
			ticket.setTitle(tickets.getTitle());
			ticket.setDescription(tickets.getDescription());
			ticket.setSolution(tickets.getSolution());
			ticket.setName(tickets.getName());
			ticket.setType(tickets.getType());
			ticket.setPicByte(decompressBytes(tickets.getPicByte()));
			ticket.setStatus(tickets.getStatus());
			ticket.setCriticalLevel(tickets.getCriticalLevel());
			ticket.setIsResolved(tickets.getIsResolved());
			ticket.setAssignedToEmployee(tickets.getAssignedToEmployee());
			ticket.setIsAssigned(tickets.getIsAssigned());
			ticket.setEmployeeName(tickets.getEmployeeName());
			ticket.setProjectName(tickets.getProjectName());
			responseList.add(ticket);
		}

		if (responseList.size() == 0) {
			return null;
		} else {
			return responseList;
		}
	}

	@Override
	public Ticket_Table updateTicketByAdmin(Ticket_Table newticket) {
		try {

			return ticketRepository.save(newticket);
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public Project_Table addProject(Project_Table project) {

		try {

			List<Project_Table> projectList = projectRepository.findAll();
			for (Project_Table isExists : projectList) {
				if (isExists.getProjectName().equalsIgnoreCase(project.getProjectName())) {
					throw new EbugException("Project already Exists");
				}
			}

			return projectRepository.save(project);
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public List<Project_Table> getAllProject() {
		try {
			return projectRepository.findAll();
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public Project_Table getProjectByID(Long id) {
		try {
			Optional<Project_Table> data = projectRepository.findById(id);
			if (data.isPresent())
				return data.get();
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
		return null;
	}

	@Override
	public Ticket_Table assignTicketToEmployeeByAdmin(Ticket_Table newTicket) {
		// TODO Auto-generated method stub

		try {
			return this.ticketRepository.save(newTicket);

		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}

	}

	@Override
	public Ticket_Table getTicketById(Long id) {
		// TODO Auto-generated method stub
		try {
			Optional<Ticket_Table> data = ticketRepository.findById(id);
			if (data.isPresent())
				return data.get();
			else {
				return null;
			}
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}

	}

	@Override
	public Employee_Table registerEmployee(Employee_Table employee) {
		try {
			List<Employee_Table> EmployeeList = employeeRepository.findAll();
			for (Employee_Table isExists : EmployeeList) {
				if (isExists.getEmailId().equalsIgnoreCase(employee.getEmailId())) {
					throw new EbugException("Employee already Exists");
				}
			}
			employee.setRole("employee");
			return employeeRepository.save(employee);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public long countProject() {

		return this.projectRepository.count();
	}

	@Override
	public long countEmployee() {

		return this.employeeRepository.countEmployee();
	}

	@Override
	public long countPending() {
		return this.ticketRepository.countUnresolved();
	}

	@Override
	public List<Ticket_Table> updateticketList() {
		List<Ticket_Table> ticketList = ticketRepository.findAllTicketForUpdate();

		if (ticketList.size() == 0) {
			return null;
		} else {
			return ticketList;
		}
	}
	
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}

}
