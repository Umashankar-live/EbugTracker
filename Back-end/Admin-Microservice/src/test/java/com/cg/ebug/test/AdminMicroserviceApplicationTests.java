package com.cg.ebug.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.ebug.dao.EmployeeRepositroy;
import com.cg.ebug.dao.ProjectRepository;
import com.cg.ebug.dao.TicketRepository;
import com.cg.ebug.entity.EmployeeTable;
import com.cg.ebug.entity.ProjectTable;
import com.cg.ebug.entity.TicketTable;
import com.cg.ebug.service.IAdminService;

@RunWith(SpringRunner.class)
@SpringBootTest
class AdminMicroserviceApplicationTests {

	@Autowired
	private IAdminService service;

	@MockBean
	private TicketRepository ticketRepository;

	@MockBean
	private ProjectRepository projectRepository;

	@MockBean
	private EmployeeRepositroy employeeRepositroy;

	@Test
	public void addEmployee() {
		EmployeeTable employee = new EmployeeTable(1, "Umashankar", "Bhagat", "Project001", "uma.tuchwud@gmail.com",
				70572774, "12345", "employee");

		when(employeeRepositroy.save(employee)).thenReturn(employee);

		assertEquals(employee.getEmpId(), service.registerEmployee(employee).getEmpId());
	}
	
	

	@Test
	public void addProject() {
		ProjectTable project = new ProjectTable(1L, "Project001", "this is the descrption", "Angular", "Springboot",
				"Rupa Rani");

		when(projectRepository.save(project)).thenReturn(project);
		
		assertEquals(project.getProjectId(), service.addProject(project).getProjectId());
		
	}
	
	@Test
	public void assignTicketToEmployeeByAdmin() {
		byte[] bytes = new byte[120];
		TicketTable ticket = new TicketTable(1L, 1001, "title", "description", "solution", "name", "type", bytes,
				"open", "low", false, 1001, "employeeName", false, "projectName");

		when(ticketRepository.save(ticket)).thenReturn(ticket);
		Assertions.assertEquals(ticket.getAssignedToEmployee(),
				service.assignTicketToEmployeeByAdmin(ticket).getAssignedToEmployee());

	}
	
	
	@Test
	void ListAllProject()
	{
		

		List<ProjectTable> projectList = new ArrayList<>(); 
		ProjectTable p1 = new ProjectTable(1L, "Project001", "this is the descrption", "Angular", "Springboot",
				"Rupa Rani");
		
		ProjectTable p2 =  new ProjectTable(2L, "Project001", "this is the descrption", "Angular", "Springboot",
				"Rupa Rani");
		
		
		projectList.add(p1);
		projectList.add(p2);
		
		
		when(service.getAllProject()).thenReturn(projectList);
		List<ProjectTable> proList = service.getAllProject();
		assertNotNull(proList);
		assertFalse(proList.isEmpty());
	}
	
	
	@Test
	void  updateTicketByAdmin() {
		
		byte[] bytes = new byte[120];
		final TicketTable t1 = new TicketTable(1L, 1001, "title", "description", "solution", "name", "type", bytes,
				"open", "low", false, 1001, "employeeName", false, "projectName");
		
		when(ticketRepository.save(t1)).thenReturn(t1);
		
		final TicketTable expected = service.updateTicketByAdmin(t1);
		assertThat(expected).isNotNull();
		
		
		
		
	}
	
	
	@Test
	void getTicketById() {
		
		final Long id = 1L ;
		byte[] bytes = new byte[120];
		final TicketTable t1 = new TicketTable(1L, 1001, "title", "description", "solution", "name", "type", bytes,
				"open", "low", false, 1001, "employeeName", false, "projectName");
		
		
		when(ticketRepository.findById(id)).thenReturn(Optional.of(t1));
		
		final TicketTable expected = service.getTicketById(id);
		
		assertThat(expected).isNotNull();
		
	}
	
	
	@Test
	void getAllTickets() {
		
		List<TicketTable> ticketList = new ArrayList<>(); 
		byte[] bytes = new byte[120];
		final TicketTable t1 = new TicketTable(1L, 1001, "title", "description", "solution", "name", "type", bytes,
				"open", "low", false, 1001, "employeeName", false, "projectName");
		
		final TicketTable t2 = new TicketTable(2L, 1001, "title", "description", "solution", "name", "type", bytes,
				"open", "low", false, 1001, "employeeName", false, "projectName");
		
		
		ticketList.add(t1);
		ticketList.add(t2);
		
		
		when(service.getAllTickets()).thenReturn(ticketList);
		List<TicketTable> tickList = service.getAllTickets();
		assertNotNull(tickList);
		assertFalse(tickList.isEmpty());
		
		
	}
	
	
	@Test
	void getProjectByID() {
		
		final Long id = 1L ;
		
		final ProjectTable p1 = new ProjectTable(1L, "Project001", "this is the descrption", "Angular", "Springboot",
				"Rupa Rani");
		
		
		when(projectRepository.findById(id)).thenReturn(Optional.of(p1));
		
		final ProjectTable expected = service.getProjectByID(id);
		
		assertThat(expected).isNotNull();
		
	}
	
	
	
	@Test
	void countProject() {
		
	
		when(projectRepository.count()).thenReturn( (long) 100);
		
		long count = service.countProject();
		System.out.println("Current Project Count Is = "+count);
		assertNotNull(count);

		
		
	}
	
	
	@Test
	void countEmployee( ) {
		
		when(employeeRepositroy.countEmployee()).thenReturn((long) 10);
		
		long count = service.countEmployee();
		System.out.println("Current Employee Count Is = "+count);
		assertNotNull(count);

		
		
	}
	
	
	@Test
	void countPending() {
		
		when(ticketRepository.countUnresolved()).thenReturn((long) 8 );
		
		long count = service.countPending();
		System.out.println("Current Pending Count Is = "+count);
		assertNotNull(count);
		
		
		
	}


	
	
	
}
