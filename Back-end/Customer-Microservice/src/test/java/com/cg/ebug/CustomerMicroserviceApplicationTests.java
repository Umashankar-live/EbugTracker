package com.cg.ebug;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cg.ebug.dao.Ticket_Repository;
import com.cg.ebug.entity.TicketTable;
import com.cg.ebug.service.ICustomerService;




@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class CustomerMicroserviceApplicationTests {
	
	
	@Autowired
	private ICustomerService service;

	@MockBean
	private Ticket_Repository ticketRepository;


	@Test
	public void getTicketsByCustIdTest() {

		final Long custId = 1L ;
		byte[] bytes = new byte[120];
		List<TicketTable> ticketsList = new ArrayList<TicketTable>();

		final TicketTable t1 = new TicketTable(1L, 1L, "title", "description", "solution", "name", "type", bytes,
				"open", "low", false, 1001, "employeeName", false, "projectName");

		final TicketTable t2 = new TicketTable(2L, 1L, "title", "description", "solution", "name", "type", bytes,
				"open", "low", false, 1001, "employeeName", false, "projectName");

		ticketsList.add(t1);
		ticketsList.add(t2);

		when(ticketRepository.findTicketsByCustId(custId)).thenReturn(ticketsList);

		List<TicketTable> expectedList = service.getAllTicketsByCustomerId(custId);
		assertNotNull(expectedList);
		assertFalse(expectedList.isEmpty());


	}

	@Test
	public void getTicketByIdTest() {
		final Long id = 1L ;
		byte[] bytes = new byte[120];
		final TicketTable t1 = new TicketTable(1L, 1001, "title", "description", "solution", "name", "type", bytes,
				"open", "low", false, 1001, "employeeName", false, "projectName");

		when(ticketRepository.findTicketById(id)).thenReturn(Optional.of(t1));

		final TicketTable expected = service.viewTicketById(id);

		assertThat(expected).isNotNull();

	}
	
	@Test
	public void updateTicketByCustTest() {
		byte[] bytes = new byte[120];
		final TicketTable ticket = new TicketTable(1L, 1001, "title", "description", "solution", "name", "type", bytes,
				"open", "low", false, 1001, "employeeName", false, "projectName");
		
		when(ticketRepository.save(ticket)).thenReturn(ticket);
		
		final TicketTable expected = service.updateTicketByCustomer(ticket);
		assertThat(expected).isNotNull();
	}

	@Test
	public void raiseTicketTest() {
		final Long id = 1L ;
		byte[] bytes = new byte[120];
		final TicketTable ticket = new TicketTable(1L, 1001, "title", "description", "solution", "name", "type", bytes,
				"open", "low", false, 1001, "employeeName", false, "projectName");
		
		when(ticketRepository.save(ticket)).thenReturn(ticket);
		Optional<TicketTable> record = ticketRepository.findById(id);
		assertNotNull(record);
	}

}
