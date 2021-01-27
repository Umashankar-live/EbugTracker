package com.cg.ebug.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.Deflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cg.ebug.dao.Ticket_Repository;
import com.cg.ebug.entity.Ticket_Table;
import com.cg.ebug.exception.EntityResponse;
import com.cg.ebug.service.ICustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin("http://localhost:4200")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	@Autowired
	private Ticket_Repository ticketRepository;

	@GetMapping(path = "/viewTicketById/{id}")
	public EntityResponse viewTicketById(@PathVariable("id") Long id) {
		// return new ResponseEntity<Ticket_Table>(customerService.viewTicketById(id),
		// HttpStatus.OK);
		EntityResponse errorDetails = new EntityResponse();
		Ticket_Table getTicket = customerService.viewTicketById(id);
		if (getTicket != null) {
			errorDetails.setMessage("Ticket viewed successfully");
			errorDetails.setError(false);
			errorDetails.setTicketTable(getTicket);
			return errorDetails;
		} else {
			errorDetails.setMessage("Ticket Not found");
			errorDetails.setError(true);
			return errorDetails;
		}
	}

	@PutMapping("/updateTicketByCustomer/{ticketId}")
	ResponseEntity<Ticket_Table> updateTicketsByCustomer(@RequestBody Ticket_Table ticket,
			@PathVariable("ticketId") Long ticketId) {
		Ticket_Table newTicket = this.customerService.viewTicketById(ticketId);
		System.out.println(newTicket);
		newTicket.setTitle(ticket.getTitle());
		newTicket.setDescription(ticket.getDescription());

		return new ResponseEntity<Ticket_Table>(this.customerService.updateTicketByCustomer(newTicket), HttpStatus.OK);
	}

	@SuppressWarnings("unused")
	@PostMapping("/upload")
	public EntityResponse uplaodImage(@RequestParam("user") String user, @RequestParam("customerId") String custId,
			@RequestParam("file") MultipartFile file) throws IOException {

		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		EntityResponse response = new EntityResponse();
		long customerId = Long.parseLong(custId);
		Ticket_Table ticket = new ObjectMapper().readValue(user, Ticket_Table.class);

		Ticket_Table record = new Ticket_Table(customerId, ticket.getTitle(), ticket.getDescription(),
				file.getOriginalFilename(), file.getContentType(), compressBytes(file.getBytes()));
		record.setIsResolved(false);
		record.setSolution("pending");
		record.setStatus("pending");
		record.setIsAssigned(false);
		record.setCriticalLevel("low");
		if (record != null) {
			ticketRepository.save(record);
			response.setMessage("Ticket Raised successfully");
			response.setError(false);
			response.setTicketTable(record);
			return response;
		} else {
			response.setMessage("Couldn't raise ticket");
			response.setError(true);
			return response;
		}
	}

	@GetMapping(path = { "/viewTicketByCustId/{custId}" })
	public List<Ticket_Table> getImage(@PathVariable("custId") Long custId) throws IOException {
		EntityResponse response = new EntityResponse();
		List<Ticket_Table> responseList = customerService.getAllTicketsByCustomerId(custId);
		if (responseList != null && responseList.size() != 0) {
			response.setMessage("Tickets Fetched successfully");
			response.setError(false);
			response.setListOfTickets(responseList);
			return responseList;
		} else {
			response.setMessage("You have not raised any ticket");
			response.setError(true);
			return responseList;
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
