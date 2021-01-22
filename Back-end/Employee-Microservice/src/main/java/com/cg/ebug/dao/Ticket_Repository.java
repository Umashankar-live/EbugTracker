package com.cg.ebug.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.ebug.entity.Ticket_Table;

public interface Ticket_Repository extends JpaRepository<Ticket_Table, Long> {

	@Query(value = "Select * from TICKET_TABLE a where a.status_id=:id",  nativeQuery = true)
	public List<Ticket_Table> FindTicketByStatusId( @Param("id") Long id);
	
	@Query(value = "Select * from TICKET_TABLE a where a.critical_id=:id",  nativeQuery = true)
	public List<Ticket_Table> FindTicketByCriticalLevelId( @Param("id") Long id);
	
	@Query(value = "Select * from TICKET_TABLE a where a.project_id=:id",  nativeQuery = true)
	public List<Ticket_Table> FindTicketByProjectId( @Param("id") Long id);
}
