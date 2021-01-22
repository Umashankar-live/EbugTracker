package com.cg.ebug.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.ebug.entity.CriticalLevel_Table;
import com.cg.ebug.entity.Employee_Table;
import com.cg.ebug.entity.Status_Table;
import com.cg.ebug.entity.Ticket_Table;
import com.cg.ebug.service.IEmplyeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	IEmplyeeService employeeService;
	
	@PostMapping("/addstatus")
	ResponseEntity<Status_Table> createStatus(@RequestBody Status_Table status) {
	    return new ResponseEntity<Status_Table>(employeeService.addStatus(status), HttpStatus.OK);
	}
	
	@PostMapping("/addcritical")
	ResponseEntity<CriticalLevel_Table> createCriticalLevel(@RequestBody CriticalLevel_Table critical) {
	    return new ResponseEntity<CriticalLevel_Table>(employeeService.addCriticalLevel(critical), HttpStatus.OK);
	}
	
	@PostMapping("/loginEmployee")
	ResponseEntity<Employee_Table> loginEmployee(@RequestBody Employee_Table employee) {
	    return new ResponseEntity<Employee_Table>(employeeService.login(employee.getEmailId(), employee.getPassword()), HttpStatus.OK);
	}
	
	@GetMapping("/ticketbystatusid/{id}/{employeeId}")
	ResponseEntity<List<Ticket_Table>> getTicketsByStatusId(@PathVariable("id") Long id, @PathVariable("employeeId") Long employeeId) {
	    return new ResponseEntity<List<Ticket_Table>>(employeeService.getTicketByStatus(id,employeeId), HttpStatus.OK);
	}
	
	@GetMapping("/ticketbycriticalid/{id}/{employeeId}")
	ResponseEntity<List<Ticket_Table>> getTicketsByCriticalId(@PathVariable("id") Long id,@PathVariable("employeeId") Long employeeId) {
	    return new ResponseEntity<List<Ticket_Table>>(employeeService.getTicketByCriticalLevelId(id,employeeId), HttpStatus.OK);
	}
	
	@GetMapping("/ticketbyprojectid/{id}/{employeeId}")
	ResponseEntity<List<Ticket_Table>> getTicketsByProjectId(@PathVariable("id") Long id,@PathVariable("employeeId") Long employeeId) {
	    return new ResponseEntity<List<Ticket_Table>>(employeeService.getTicketByProjectId(id,employeeId), HttpStatus.OK);
	}

}
