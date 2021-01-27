package com.cg.ebug.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cg.ebug.entity.Employee_Table;
import com.cg.ebug.entity.Project_Table;
import com.cg.ebug.entity.Ticket_Table;
import com.cg.ebug.exception.UserAlreadyRegisterd;
import com.cg.ebug.service.IAdminService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private IAdminService adminService;
	
	@PostMapping(path = "/registerEmployee")
	ResponseEntity<Employee_Table> registerEmployee(@RequestBody Employee_Table employee) {
		Employee_Table response = adminService.registerEmployee(employee);
		if(response == null) {

			throw new UserAlreadyRegisterd("400", "User Alredy Register");			
		}
		 return new ResponseEntity<Employee_Table>(response, HttpStatus.OK);
	}
	
	@GetMapping("/updateticketList")
	ResponseEntity<List<Ticket_Table>> getAllTicketForUpdate() {
	    return new ResponseEntity<List<Ticket_Table>>(adminService.updateticketList(), HttpStatus.OK);
	}
	
	@GetMapping("/tickets")
	ResponseEntity<List<Ticket_Table>> getAllTickets() {
	    return new ResponseEntity<List<Ticket_Table>>(adminService.getAllTickets(), HttpStatus.OK);
	}
	
	@GetMapping("/tickets/{ticketId}")
	ResponseEntity<Ticket_Table> getTicketById(@PathVariable("ticketId") Long id) {
	    return new ResponseEntity<Ticket_Table>(adminService.getTicketById(id), HttpStatus.OK);
	}
	
	
	
	@PutMapping("/updateticket/{ticketId}")
	ResponseEntity<Ticket_Table> updateTicketsByAdmin(@RequestBody Ticket_Table ticket,@PathVariable("ticketId") Long ticketId) {
		Ticket_Table newTicket = this.adminService.getTicketById(ticketId);
		System.out.println(newTicket);
		newTicket.setCriticalLevel(ticket.getCriticalLevel());
		newTicket.setStatus(ticket.getStatus());
		newTicket.setProjectName(ticket.getProjectName());

	    return new ResponseEntity<Ticket_Table>(adminService.updateTicketByAdmin(newTicket), HttpStatus.OK);
	}
	
	@GetMapping("/assignticket/{ticketID}/{empID}")
	ResponseEntity<Ticket_Table> assignTicketToEmployeeByAdmin(@PathVariable("ticketID") Long ticketId, @PathVariable("empID") Long empId) {
		Ticket_Table newTicket = this.adminService.getTicketById(ticketId);
		System.out.println(newTicket);
		newTicket.setAssignedToEmployee(empId);
		newTicket.setIsAssigned(true);
		return new ResponseEntity<Ticket_Table>(adminService.assignTicketToEmployeeByAdmin(newTicket), HttpStatus.OK);
	}
	
	
	// Project
	@PostMapping("/createproject")
	ResponseEntity<Project_Table> createTickets(@RequestBody Project_Table project) {
	    return new ResponseEntity<Project_Table>(adminService.addProject(project), HttpStatus.OK);
	}
	
	@GetMapping("/getproject")
	ResponseEntity<List<Project_Table>> getAllProject() {
	    return new ResponseEntity<List<Project_Table>>(adminService.getAllProject(), HttpStatus.OK);
	}
	
	@GetMapping("/getproject/{id}")
	ResponseEntity<Project_Table> getProjectByID(@PathVariable("id") Long id) {
	    return new ResponseEntity<Project_Table>(adminService.getProjectByID(id), HttpStatus.OK);
	}
	
	
	// count of field
	
	
    @GetMapping(value = "/countProject")
	public long countProject() {
	  return adminService.countProject();
		
    }
    
    @GetMapping(value = "/countPending")
	public long countPending() {
	  return adminService.countPending();
		
    }
    
    @GetMapping(value = "/countEmployee")
	public long countEmployee() {
	  return adminService.countEmployee();
		
    }
    
    
    
	
	
}
