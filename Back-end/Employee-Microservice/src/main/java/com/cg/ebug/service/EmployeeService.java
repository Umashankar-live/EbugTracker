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

import com.cg.ebug.dao.EmployeeRepository;
import com.cg.ebug.dao.TicketRepository;
import com.cg.ebug.entity.EmployeeTable;
import com.cg.ebug.entity.TicketTable;

@Service
@Transactional
public class EmployeeService implements IEmployeeService {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public TicketTable getTicketById(Long ticketId) {
		try {
			Optional<TicketTable> data = ticketRepository.findById(ticketId);
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
	public EmployeeTable getEmpList(Long empId) {

		return this.employeeRepository.findById(empId).get();
	}

	@Override
	public TicketTable assignTicketToEmployeeByEmployee(TicketTable newTicket) {
		try {

			return ticketRepository.save(newTicket);
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public TicketTable updateTicketByEmployeeForSolution(TicketTable newTicket) {
		try {

			return ticketRepository.save(newTicket);
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}

	}

	@Override
	public List<TicketTable> getTicketList(Long empId) {
		List<TicketTable> responseList = new ArrayList<TicketTable>();
		List<TicketTable> ticketList = ticketRepository.FindTicketByEmployee(empId);
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
