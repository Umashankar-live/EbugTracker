package com.cg.ebug.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.ebug.entity.EmployeeTable;


@Repository
public interface EmployeeDao extends JpaRepository<EmployeeTable, Long> {

	
	
	@Query(value = "Select * from EMPLOYEE_TABLE a where a.role = 'employee' ",  nativeQuery = true)
	List<EmployeeTable> getEmployeeList();

	EmployeeTable findByEmailIdAndPassword(String tempEmaiId, String tempPassword);

	
	

}
