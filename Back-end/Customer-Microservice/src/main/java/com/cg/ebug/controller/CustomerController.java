package com.cg.ebug.controller;

import java.io.IOException;
import java.util.List;

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
import com.cg.ebug.entity.TicketTable;
import com.cg.ebug.exception.EntityResponse;
import com.cg.ebug.service.ICustomerService;

/*
 * @Autowired - The process of injection spring bean dependencies while initializing it
 * @RequestMapping - for configuring URI mapping in controller handler methods 
 * @PathVariable -  for mapping dynamic values from the URI to handler method arguments.
 * @CrossOrigin - enables cross-origin resource sharing only for this specific method. By default, its allows all origins, 
 *                all headers, and the HTTP methods specified in the @RequestMapping annotation
 * @ResponseBody - annotation maps the HttpRequest body to a transfer or domain object
 */

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
		TicketTable getTicket = customerService.viewTicketById(id);
		if (getTicket != null) {
			errorDetails.setMessage("Ticket is viewed successfully");
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
	ResponseEntity<TicketTable> updateTicketsByCustomer(@RequestBody TicketTable ticket,
			@PathVariable("ticketId") Long ticketId) {
		TicketTable newTicket = this.customerService.viewTicketById(ticketId);
		System.out.println(newTicket);
		newTicket.setTitle(ticket.getTitle());
		newTicket.setDescription(ticket.getDescription());
		newTicket.setIsResolved(ticket.getIsResolved());

		return new ResponseEntity<TicketTable>(this.customerService.updateTicketByCustomer(newTicket), HttpStatus.OK);
	}

	@PostMapping("/upload")
	public EntityResponse uplaodImage( @RequestParam("user") String user ,@RequestParam("customerId") String custId, @RequestParam("file") MultipartFile file) throws IOException {

		EntityResponse response = new EntityResponse();
		TicketTable record = customerService.raiseTicket(user, custId, file);
		if(record != null) {
			ticketRepository.save(record);
			response.setMessage("Ticket Raised successfully");
			response.setError(false);
			response.setTicketTable(record);
			return response;
		}else {
			response.setMessage("Couldn't raise ticket");
			response.setError(true);
			return response;
		}
	}

	@GetMapping(path = { "/viewTicketByCustId/{custId}" })
	public List<TicketTable> getImage(@PathVariable("custId") Long custId) throws IOException {
		EntityResponse response = new EntityResponse();
		List<TicketTable> responseList = customerService.getAllTicketsByCustomerId(custId);
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

}
