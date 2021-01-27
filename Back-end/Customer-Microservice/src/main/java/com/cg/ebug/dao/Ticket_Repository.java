package com.cg.ebug.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.ebug.entity.Ticket_Table;

public interface Ticket_Repository extends JpaRepository<Ticket_Table, Long> {

	Optional<Ticket_Table> findTicketById(Long id);

	@Query(value = "select t from Ticket_Table t where t.custId=:custId" )
	List<Ticket_Table> findTicketsByCustId(@Param("custId") long custId);
}
