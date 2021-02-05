package com.cg.ebug;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.ebug.dao.TicketRepository;
import com.cg.ebug.entity.TicketTable;
import com.cg.ebug.service.IEmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
class EmployeeMicroserviceApplicationTests {

	@Autowired
	private IEmployeeService service;

	@MockBean
	private TicketRepository ticketRepository;

	@Test
	public void assignTicketToOtherEmployee() {
		byte[] bytes = new byte[120];
		TicketTable ticket = new TicketTable(1L, 1001, "title", "description", "solution", "name", "type", bytes,
				"open", "low", false, 1001, "employeeName", false, "projectName");

		when(ticketRepository.save(ticket)).thenReturn(ticket);
		Assertions.assertEquals(ticket.getAssignedToEmployee(),
				service.assignTicketToEmployeeByEmployee(ticket).getAssignedToEmployee());

	}

	@Test
	public void sendSolutionToCutomer() {
		byte[] bytes = new byte[120];
		TicketTable ticket = new TicketTable(1L, 1001, "title", "description", "solution", "name", "type", bytes,
				"open", "low", false, 1001, "employeeName", false, "projectName");

		when(ticketRepository.save(ticket)).thenReturn(ticket);
		 Assertions.assertEquals(ticket.getSolution(),service.updateTicketByEmployeeForSolution(ticket).getSolution())
		;

	}
	

}