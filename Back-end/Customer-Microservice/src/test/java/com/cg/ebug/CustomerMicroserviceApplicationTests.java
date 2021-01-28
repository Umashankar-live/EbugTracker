package com.cg.ebug;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cg.ebug.dao.Ticket_Repository;
import com.cg.ebug.entity.Ticket_Table;
import com.cg.ebug.service.CustomerService;




@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class EbugApplicationTests {
	
	@Autowired
	private CustomerService service;
	
	@MockBean
	private Ticket_Repository ticketRepository;
	
	

//	@Test
//	public void getTicketsByCustIdTest() {
//		int custId = 1;
//		when(ticketRepository.findTicketsByCustId(custId)).thenReturn(Stream.of(new Ticket_Table(1, "title", "description", "solution",
//				"status", "criticalLevel", false, 1, "employeeName", false, "projectName")).collect(Collectors.toList()));
//		assertEquals(2, service.getAllTicketsByCustomerId(custId).size());
//	}
//	
	

}
