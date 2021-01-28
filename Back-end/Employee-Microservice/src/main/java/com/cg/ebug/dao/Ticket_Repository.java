package com.cg.ebug.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.ebug.entity.Ticket_Table;

public interface Ticket_Repository extends JpaRepository<Ticket_Table, Long> {

	
	// employee
	@Query(value = "Select * from TICKET_TABLE a WHERE  a.status !='close' AND a.assigned_To_Employee=:employeeId", nativeQuery = true)
	public List<Ticket_Table> FindTicketByEmployee( @Param("employeeId") Long employeeId);
	
}
	
