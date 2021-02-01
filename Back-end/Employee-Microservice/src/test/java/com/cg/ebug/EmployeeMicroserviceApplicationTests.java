package com.cg.ebug;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.cg.ebug.dao.Ticket_Repository;
import com.cg.ebug.entity.Ticket_Table;
import com.cg.ebug.service.IEmployeeService;

@SpringBootTest
class EmployeeMicroserviceApplicationTests {

	@Autowired
	private IEmployeeService service ;
	
	@MockBean
	private Ticket_Repository ticketRepository ;
	
	
	@Test
	public void assignTicketToOtherEmployee()
	{
		byte[] bytes= new byte[120];
	    //Ticket_Table ticket = new Ticket_Table(101,123456,"title1","this is description","ABC","XYZ",bytes);
		
		//when(ticketRepository.save(ticket)).thenReturn(ticket);
		//Assertions.assertEquals(ticket.getAssignedToEmployee(),service.assignTicketToEmployeeByEmployee(ticket).getAssignedToEmployee());
		
	}
	
	@Test
	public void sendSolutionToCutomer()
	{
		byte[] bytes= new byte[120];
		//Ticket_Table ticket = new Ticket_Table(101,123456,"title1","this is description","ABC","XYZ",bytes);
		
		//when(ticketRepository.save(ticket)).thenReturn(ticket);
		//Assertions.assertEquals(ticket.getSolution(), service.updateTicketByEmployeeForSolution(ticket).getSolution())
		;
		
	}
	
}