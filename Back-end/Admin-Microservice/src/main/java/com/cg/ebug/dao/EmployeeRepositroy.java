package com.cg.ebug.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.ebug.entity.EmployeeTable;

public interface EmployeeRepositroy  extends JpaRepository<EmployeeTable, Long> {

	@Query(value = "select e from EmployeeTable e where e.emailId=:emailId and e.password=:password" )
	EmployeeTable login(String emailId,String password);

	@Query(value = "SELECT count(e) FROM EmployeeTable e WHERE e.role='employee'")
	long countEmployee();
	
	}
