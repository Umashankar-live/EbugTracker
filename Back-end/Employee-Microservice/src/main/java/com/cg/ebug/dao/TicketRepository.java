package com.cg.ebug.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.ebug.entity.TicketTable;

public interface TicketRepository extends JpaRepository<TicketTable, Long> {

	
	// employee
	@Query(value = "Select * from TICKET_TABLE a WHERE  a.status !='close' AND a.assigned_To_Employee=:employeeId", nativeQuery = true)
	public List<TicketTable> FindTicketByEmployee( @Param("employeeId") Long employeeId);
	
}
	
