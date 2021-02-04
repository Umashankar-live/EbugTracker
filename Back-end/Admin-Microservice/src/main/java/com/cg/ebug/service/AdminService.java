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

import com.cg.ebug.dao.EmployeeRepositroy;
import com.cg.ebug.dao.ProjectRepository;
import com.cg.ebug.dao.TicketRepository;
import com.cg.ebug.entity.EmployeeTable;
import com.cg.ebug.entity.ProjectTable;
import com.cg.ebug.entity.TicketTable;
import com.cg.ebug.exception.EbugException;

@Service
@Transactional
public class AdminService implements IAdminService {

	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private EmployeeRepositroy employeeRepository;

	@Override
	public List<TicketTable> getAllTickets() {
		List<TicketTable> responseList = new ArrayList<TicketTable>();
		List<TicketTable> ticketList  = ticketRepository.findAllTicket();
		for (TicketTable tickets : ticketList) {
			TicketTable ticket = new TicketTable();
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
	public TicketTable updateTicketByAdmin(TicketTable newticket) {
		try {

			return ticketRepository.save(newticket);
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public ProjectTable addProject(ProjectTable project) {

		try {

			List<ProjectTable> projectList = projectRepository.findAll();
			for (ProjectTable isExists : projectList) {
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
	public List<ProjectTable> getAllProject() {
		try {
			return projectRepository.findAll();
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public ProjectTable getProjectByID(Long id) {
		try {
			Optional<ProjectTable> data = projectRepository.findById(id);
			if (data.isPresent())
				return data.get();
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
		return null;
	}

	@Override
	public TicketTable assignTicketToEmployeeByAdmin(TicketTable newTicket) {
		// TODO Auto-generated method stub

		try {
			return this.ticketRepository.save(newTicket);

		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}

	}

	@Override
	public TicketTable getTicketById(Long id) {
		// TODO Auto-generated method stub
		try {
			Optional<TicketTable> data = ticketRepository.findById(id);
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
	public EmployeeTable registerEmployee(EmployeeTable employee) {
		try {
			List<EmployeeTable> EmployeeList = employeeRepository.findAll();
			for (EmployeeTable isExists : EmployeeList) {
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
	public List<TicketTable> updateticketList() {
		List<TicketTable> ticketList = ticketRepository.findAllTicketForUpdate();

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
