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

import com.cg.ebug.entity.EmployeeTable;
import com.cg.ebug.entity.ProjectTable;
import com.cg.ebug.entity.TicketTable;
import com.cg.ebug.exception.UserAlreadyRegisterd;
import com.cg.ebug.service.IAdminService;

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
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private IAdminService adminService;

	@PostMapping(path = "/registerEmployee")
	ResponseEntity<EmployeeTable> registerEmployee(@RequestBody EmployeeTable employee) {
		EmployeeTable response = adminService.registerEmployee(employee);
		if (response == null) {

			throw new UserAlreadyRegisterd("400", "User Already Register");
		}
		return new ResponseEntity<EmployeeTable>(response, HttpStatus.OK);
	}

	@GetMapping("/updateticketList")
	ResponseEntity<List<TicketTable>> getAllTicketForUpdate() {
		return new ResponseEntity<List<TicketTable>>(adminService.updateticketList(), HttpStatus.OK);
	}

	@GetMapping("/tickets")
	ResponseEntity<List<TicketTable>> getAllTickets() {
		return new ResponseEntity<List<TicketTable>>(adminService.getAllTickets(), HttpStatus.OK);
	}

	@GetMapping("/tickets/{ticketId}")
	ResponseEntity<TicketTable> getTicketById(@PathVariable("ticketId") Long id) {
		return new ResponseEntity<TicketTable>(adminService.getTicketById(id), HttpStatus.OK);
	}

	@PutMapping("/updateticket/{ticketId}")
	ResponseEntity<TicketTable> updateTicketsByAdmin(@RequestBody TicketTable ticket,
			@PathVariable("ticketId") Long ticketId) {
		TicketTable newTicket = this.adminService.getTicketById(ticketId);
		System.out.println(newTicket);
		newTicket.setCriticalLevel(ticket.getCriticalLevel());
		newTicket.setStatus(ticket.getStatus());
		newTicket.setProjectName(ticket.getProjectName());

		return new ResponseEntity<TicketTable>(adminService.updateTicketByAdmin(newTicket), HttpStatus.OK);
	}

	@GetMapping("/assignticket/{ticketID}/{empID}/{empName}")
	ResponseEntity<TicketTable> assignTicketToEmployeeByAdmin(@PathVariable("ticketID") Long ticketId, @PathVariable("empID") Long empId,
			@PathVariable("empName") String employeeName) {
		TicketTable newTicket = this.adminService.getTicketById(ticketId);
		System.out.println(newTicket);
		newTicket.setAssignedToEmployee(empId);
		newTicket.setEmployeeName(employeeName);
		newTicket.setIsAssigned(true);
		return new ResponseEntity<TicketTable>(adminService.assignTicketToEmployeeByAdmin(newTicket), HttpStatus.OK);
	}

	// Project
	@PostMapping("/createproject")
	ResponseEntity<ProjectTable> createTickets(@RequestBody ProjectTable project) {
		return new ResponseEntity<ProjectTable>(adminService.addProject(project), HttpStatus.OK);
	}

	@GetMapping("/getproject")
	ResponseEntity<List<ProjectTable>> getAllProject() {
		return new ResponseEntity<List<ProjectTable>>(adminService.getAllProject(), HttpStatus.OK);
	}

	@GetMapping("/getproject/{id}")
	ResponseEntity<ProjectTable> getProjectByID(@PathVariable("id") Long id) {
		return new ResponseEntity<ProjectTable>(adminService.getProjectByID(id), HttpStatus.OK);
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
