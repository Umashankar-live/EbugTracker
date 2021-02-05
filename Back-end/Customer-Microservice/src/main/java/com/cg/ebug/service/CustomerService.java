package com.cg.ebug.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cg.ebug.dao.Ticket_Repository;
import com.cg.ebug.entity.TicketTable;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class CustomerService implements ICustomerService {

	@Autowired
	private Ticket_Repository ticketRepository;

	@Override
	public TicketTable viewTicketById(Long id) {
		try {
			Optional<TicketTable> data = ticketRepository.findTicketById(id);
			if (data.isPresent())
				return data.get();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
		return null;
	}

	@Override
	public TicketTable updateTicketByCustomer(TicketTable ticket) {
		try {
			return ticketRepository.save(ticket);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}

	}

	@Override
	public List<TicketTable> getAllTicketsByCustomerId(long custId) {
		List<TicketTable> responseList = new ArrayList<TicketTable>();
		List<TicketTable> ticketList = ticketRepository.findTicketsByCustId(custId);
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

	@Override
	public TicketTable raiseTicket(String user, String custId, MultipartFile file){
		try {
			long customerId=Long.parseLong(custId);  
			TicketTable ticket = new ObjectMapper().readValue(user, TicketTable.class);
			TicketTable record = new TicketTable(customerId, ticket.getTitle(), ticket.getDescription(), file.getOriginalFilename(), file.getContentType(), compressBytes(file.getBytes()));
			record.setIsResolved(false);
			record.setSolution("Pending");
			record.setStatus("pending");
			record.setIsAssigned(false);
			record.setCriticalLevel("low");
			return ticketRepository.save(record);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	// compress the image bytes before storing it in the database
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}
}
