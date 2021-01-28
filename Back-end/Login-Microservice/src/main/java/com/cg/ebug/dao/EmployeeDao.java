package com.cg.ebug.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.ebug.entity.Employee_Table;


@Repository
public interface EmployeeDao extends JpaRepository<Employee_Table, Long> {

	
	
	@Query(value = "Select * from EMPLOYEE_TABLE a where a.role = 'employee' ",  nativeQuery = true)
	List<Employee_Table> getEmployeeList();

	Employee_Table findByEmailIdAndPassword(String tempEmaiId, String tempPassword);

	
	

}
