package com.cg.ebug;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.ebug.dao.Employee_Repositroy;
import com.cg.ebug.dao.Project_Repository;
import com.cg.ebug.dao.Ticket_Repository;
import com.cg.ebug.entity.Employee_Table;
import com.cg.ebug.entity.Project_Table;
import com.cg.ebug.service.IAdminService;

@SpringBootTest
class AdminMicroserviceApplicationTests {
	
	@Autowired
	private IAdminService service ;
	
	@MockBean
	private Ticket_Repository ticketRepository ;
	
	@MockBean
	private Project_Repository projectRepository ;
	
	@MockBean
	private Employee_Repositroy employeeRepositroy ;
	

	@Test
	public void addEmployee() {
		Employee_Table employee = new Employee_Table(1, "Umashankar", "Bhagat", "Project001", "uma.tuchwud@gmail.com",70572774, "12345", "employee");

		when(employeeRepositroy.save(employee)).thenReturn(employee);

		Assertions.assertEquals(employee.getEmpId(), service.registerEmployee(employee).getEmpId());
	}
	
	
	  @Test public void addProject() 
	  { Project_Table project = new Project_Table(1L, "Project001", "this is the descrption","Angular","Springboot","Rupa Rani");
	  
	  when(projectRepository.save(project));
	  System.out.println(project.getProjectId());
	  Assertions.assertEquals(project.getProjectName(),
	  service.addProject(project).getProjectName()); }	

}
