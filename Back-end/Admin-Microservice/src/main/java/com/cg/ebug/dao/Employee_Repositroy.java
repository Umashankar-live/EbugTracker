package com.cg.ebug.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.ebug.entity.Employee_Table;

public interface Employee_Repositroy  extends JpaRepository<Employee_Table, Long> {

	@Query(value = "select e from Employee_Table e where e.emailId=:emailId and e.password=:password" )
	Employee_Table login(String emailId,String password);

	@Query(value = "SELECT count(e) FROM Employee_Table e WHERE e.role='employee'")
	long countEmployee();
	
	}
