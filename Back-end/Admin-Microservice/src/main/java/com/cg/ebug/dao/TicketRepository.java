package com.cg.ebug.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.ebug.entity.TicketTable;

public interface TicketRepository extends JpaRepository<TicketTable, Long> {

	//admin
	@Query(value = "SELECT count(e) FROM TicketTable e WHERE e.isResolved=false and e.isAssigned=false")
	long countUnresolved();

	@Query(value = "Select * from TICKET_TABLE a WHERE a.is_resolved =false AND a.is_assigned =false ",  nativeQuery = true)
	public List<TicketTable> findAllTicket();

	@Query(value = "Select * from TICKET_TABLE ",  nativeQuery = true)
	List<TicketTable> findAllTicketForUpdate();




}
