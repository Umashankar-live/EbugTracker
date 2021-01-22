package com.cg.ebug.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ebug.entity.Ticket_Table;
import com.cg.ebug.exception.UserAlreadyRegisterd;
import com.cg.ebug.service.ICustomerService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private ICustomerService customerService;
		
	
	@PostMapping(path = "/viewTicketById")
	ResponseEntity<Ticket_Table> viewTicketById(@RequestBody Ticket_Table ticket){
		return new ResponseEntity<Ticket_Table>(customerService.viewTicketById(ticket.getId()), HttpStatus.OK);
	}
	
	@PostMapping("/createticket")
	ResponseEntity<Ticket_Table> createTickets(@RequestBody Ticket_Table ticket) {
		System.out.println(ticket.toString());
	    return new ResponseEntity<Ticket_Table>(customerService.createTicket(ticket), HttpStatus.OK);
	}
	
	@GetMapping("/ticketbystatusidbycus/{id}/{employeeId}")
	ResponseEntity<List<Ticket_Table>> getTicketsByStatusIdByCus(@PathVariable("id") Long id, @PathVariable("employeeId") Long employeeId) {
	    return new ResponseEntity<List<Ticket_Table>>(customerService.getTicketByStatusByCus(id,employeeId), HttpStatus.OK);
	}
	
	@GetMapping("/ticketbycriticalidbycus/{id}/{employeeId}")
	ResponseEntity<List<Ticket_Table>> getTicketsByCriticalIdByCus(@PathVariable("id") Long id,@PathVariable("employeeId") Long employeeId) {
	    return new ResponseEntity<List<Ticket_Table>>(customerService.getTicketByCriticalLevelIdByCus(id,employeeId), HttpStatus.OK);
	}
	
	
    @ExceptionHandler({ UserAlreadyRegisterd.class})
    public ResponseEntity<UserAlreadyRegisterd> handleException(UserAlreadyRegisterd ex) {
        //
    	System.out.println("exception");
    	return new ResponseEntity<UserAlreadyRegisterd>(ex, HttpStatus.OK);
    }

}
