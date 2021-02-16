package com.cg.ebug;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.ebug.controller.LoginController;
import com.cg.ebug.dao.EmployeeDao;
import com.cg.ebug.entity.EmployeeTable;
import com.cg.ebug.service.UserServiceImpl;
import com.cg.ebug.service.UserServiceInterface;

@SpringBootTest
class LoginMicroserviceApplicationTests {

	@Autowired
	private UserServiceInterface userService;
	
	@Autowired
	private LoginController controller;
	
	@MockBean
	private EmployeeDao empRepository;
	
	@Test
	void addUserTest() {
		EmployeeTable emp=new EmployeeTable(1L,"Ravi","Kumar","Ebug System","ravi123@gmail.com",876543212,"kumar1234@","Developer");
	    userService=mock(UserServiceImpl.class);
	    when(userService.addUser(emp)).thenReturn(emp);
	    
	    EmployeeTable emp2=userService.addUser(emp);
	    assertEquals(emp,emp2);
	}
	
	
	
	
	
	/**@Test
	void deleteTest() {
		List<EmployeeTable>emp=new ArrayList<>();
		EmployeeTable emp1=new EmployeeTable(1L,"Ravi","Kumar","Ebug System","ravi123@gmail.com",876543212,"kumar1234@","Developer");
		EmployeeTable emp2=new EmployeeTable(2L,"Shubham","Setti","Ebug System","shubham123@gmail.com",876234212,"shubham1234@","Project Manager");
		EmployeeTable emp3=new EmployeeTable(3L,"Aditya","Patil","Ebug System","aditya123@gmail.com",876827212,"aditya1234@","Business Analyst");
		
		emp.add(emp1);
		emp.add(emp2);
		emp.add(emp3);
		
		UserServiceInterface userService=mock(UserServiceImpl.class);
		when(userService.deleteUser(2L)).thenReturn(emp);
		List<EmployeeTable>empList=userService.deleteUser(2L);
		assertNotNull(empList);
		assertFalse(empList.isEmpty());
	}**/
	
	@Test
	void updateUserTest() {
		EmployeeTable emp=new EmployeeTable(1L,"Ravi","Kumar","Ebug System","ravi123@gmail.com",876543212,"kumar1234@","Developer");
	    userService=mock(UserServiceImpl.class);
	    userService.addUser(emp);
	    EmployeeTable emp2=new EmployeeTable(1L,"Rohit","Kumar","Ebug System","ravi123@gmail.com",876543212,"kumar1234@","Developer");
	    
	    when(userService.updateUser(emp2)).thenReturn(emp2);
	    EmployeeTable emp3=userService.updateUser(emp2);
	    assertEquals(emp2,emp3);
	}
	@Test
	void searchUserTest() {
		EmployeeTable emp5=new EmployeeTable();
		
		
		userService=mock(UserServiceImpl.class);
		when(userService.searchUser(2L)).thenReturn(emp5);
		userService.searchUser(2L);
		assertNotNull(emp5);
		
	}
	
	
	
	@Test
	void getAllUserTest() {
		List<EmployeeTable>emp=new ArrayList<>();
		EmployeeTable emp1=new EmployeeTable(1L,"Ravi","Kumar","Ebug System","ravi123@gmail.com",876543212,"kumar1234@","Developer");
		EmployeeTable emp2=new EmployeeTable(2L,"Shubham","Setti","Ebug System","shubham123@gmail.com",876234212,"shubham1234@","Project Manager");
		EmployeeTable emp3=new EmployeeTable(3L,"Aditya","Patil","Ebug System","aditya123@gmail.com",876827212,"aditya1234@","Business Analyst");
		
		emp.add(emp1);
		emp.add(emp2);
		emp.add(emp3);
		
		userService=mock(UserServiceImpl.class);
		when(userService.getAllUser()).thenReturn(emp);
		List<EmployeeTable>empList=userService.getAllUser();
		assertNotNull(empList);
		assertFalse(empList.isEmpty());
	}
	
	

}
