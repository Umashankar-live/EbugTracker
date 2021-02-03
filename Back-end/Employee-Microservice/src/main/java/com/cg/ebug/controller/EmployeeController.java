package com.cg.ebug.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ebug.entity.Ticket_Table;
import com.cg.ebug.entity.Employee_Table;
import com.cg.ebug.service.IEmployeeService;

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
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	IEmployeeService employeeService;

	// http://localhost:8094/employee/assigntickettoOther/
	@GetMapping("/assigntickettoOther/{ticketID}/{empID}/{empName}")
	ResponseEntity<Ticket_Table> assignTicketToEmployeeByAdmin(@PathVariable("ticketID") Long ticketId, @PathVariable("empID") Long empId,
			@PathVariable("empName") String employeeName) {
		Ticket_Table newTicket = this.employeeService.getTicketById(ticketId);
		System.out.println(newTicket);
		newTicket.setAssignedToEmployee(empId);
		newTicket.setEmployeeName(employeeName);
		newTicket.setIsAssigned(true);
		return new ResponseEntity<Ticket_Table>(employeeService.assignTicketToEmployeeByEmployee(newTicket), HttpStatus.OK);
	}


	// http://localhost:8094/employee/sendSolution/
	@PutMapping("/sendSolution/{ticketID}")
	ResponseEntity<Ticket_Table> sendSolutionToCustomer(@RequestBody Ticket_Table ticket,
			@PathVariable("ticketID") Long ticketId) {
		Ticket_Table newTicket = this.employeeService.getTicketById(ticketId);
		System.out.println(newTicket);
		newTicket.setSolution(ticket.getSolution());
		

		return new ResponseEntity<Ticket_Table>(employeeService.updateTicketByEmployeeForSolution(newTicket),
				HttpStatus.OK);
	}

	// http://localhost:8094/employee/getemplist/
	@GetMapping("/getemplist/{empID}")
	ResponseEntity<Employee_Table> getEmpList(@PathVariable("empID") Long empId) {
		return new ResponseEntity<Employee_Table>(this.employeeService.getEmpList(empId), HttpStatus.OK);
	}

	// http://localhost:8094/employee/getTicketlist/
	@GetMapping("/getTicketlist/{empID}")
	ResponseEntity<List<Ticket_Table>> getTicketList(@PathVariable("empID") Long empId) {
		return new ResponseEntity<List<Ticket_Table>>(this.employeeService.getTicketList(empId), HttpStatus.OK);
	}

}
