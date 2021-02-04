package com.cg.ebug.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.ebug.entity.EmployeeTable;

public interface EmployeeRepository  extends JpaRepository<EmployeeTable, Long> {

	
	
	}
