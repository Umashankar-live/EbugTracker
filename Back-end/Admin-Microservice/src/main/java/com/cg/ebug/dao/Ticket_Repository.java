package com.cg.ebug.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.ebug.entity.Ticket_Table;

public interface Ticket_Repository extends JpaRepository<Ticket_Table, Long> {

	//admin
	@Query(value = "SELECT count(e) FROM Ticket_Table e WHERE e.isResolved=false and e.isAssigned=false")
	long countUnresolved();

	@Query(value = "Select * from TICKET_TABLE a WHERE a.is_resolved =false AND a.is_assigned =false ",  nativeQuery = true)
	public List<Ticket_Table> findAllTicket();

	@Query(value = "Select * from TICKET_TABLE ",  nativeQuery = true)
	List<Ticket_Table> findAllTicketForUpdate();




}
