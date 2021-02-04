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

import com.cg.ebug.entity.TicketTable;
import com.cg.ebug.entity.EmployeeTable;
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
	ResponseEntity<TicketTable> assignTicketToEmployeeByAdmin(@PathVariable("ticketID") Long ticketId, @PathVariable("empID") Long empId,
			@PathVariable("empName") String employeeName) {
		TicketTable newTicket = this.employeeService.getTicketById(ticketId);
		System.out.println(newTicket);
		newTicket.setAssignedToEmployee(empId);
		newTicket.setEmployeeName(employeeName);
		newTicket.setIsAssigned(true);
		return new ResponseEntity<TicketTable>(employeeService.assignTicketToEmployeeByEmployee(newTicket), HttpStatus.OK);
	}


	// http://localhost:8094/employee/sendSolution/
	@PutMapping("/sendSolution/{ticketID}")
	ResponseEntity<TicketTable> sendSolutionToCustomer(@RequestBody TicketTable ticket,
			@PathVariable("ticketID") Long ticketId) {
		TicketTable newTicket = this.employeeService.getTicketById(ticketId);
		System.out.println(newTicket);
		newTicket.setSolution(ticket.getSolution());
		

		return new ResponseEntity<TicketTable>(employeeService.updateTicketByEmployeeForSolution(newTicket),
				HttpStatus.OK);
	}

	// http://localhost:8094/employee/getemplist/
	@GetMapping("/getemplist/{empID}")
	ResponseEntity<EmployeeTable> getEmpList(@PathVariable("empID") Long empId) {
		return new ResponseEntity<EmployeeTable>(this.employeeService.getEmpList(empId), HttpStatus.OK);
	}

	// http://localhost:8094/employee/getTicketlist/
	@GetMapping("/getTicketlist/{empID}")
	ResponseEntity<List<TicketTable>> getTicketList(@PathVariable("empID") Long empId) {
		return new ResponseEntity<List<TicketTable>>(this.employeeService.getTicketList(empId), HttpStatus.OK);
	}

}
