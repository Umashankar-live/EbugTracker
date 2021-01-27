package com.cg.ebug.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.ebug.entity.Employee_Table;

public interface Employee_Repository  extends JpaRepository<Employee_Table, Long> {

	
	
	}
