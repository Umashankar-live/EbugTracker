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

import com.cg.ebug.dao.Ticket_Repository;
import com.cg.ebug.entity.Ticket_Table;

@Service
@Transactional
public class CustomerService implements ICustomerService {

	@Autowired
	private Ticket_Repository ticketRepository;

	@Override
	public Ticket_Table viewTicketById(Long id) {
		try {
			Optional<Ticket_Table> data = ticketRepository.findTicketById(id);
			if (data.isPresent())
				return data.get();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
		return null;
	}

	@Override
	public Ticket_Table updateTicketByCustomer(Ticket_Table ticket) {
		try {
			return ticketRepository.save(ticket);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}

	}

	@Override
	public List<Ticket_Table> getAllTicketsByCustomerId(long custId) {
		List<Ticket_Table> responseList = new ArrayList<Ticket_Table>();
		List<Ticket_Table> ticketList = ticketRepository.findTicketsByCustId(custId);
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
		return responseList;
	}

	// uncompress the image bytes before returning it to the angular application
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
