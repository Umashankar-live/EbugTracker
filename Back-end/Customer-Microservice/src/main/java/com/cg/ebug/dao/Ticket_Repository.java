package com.cg.ebug.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.ebug.entity.TicketTable;

public interface Ticket_Repository extends JpaRepository<TicketTable, Long> {

	Optional<TicketTable> findTicketById(Long id);

	@Query(value = "select t from TicketTable t where t.custId=:custId" )
	List<TicketTable> findTicketsByCustId(@Param("custId") long custId);
}
