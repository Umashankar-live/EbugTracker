package com.cg.ebug.dao;
import org.springframework.data.jpa.repository.Query;

import com.cg.ebug.entity.Ticket_Table;

public interface Customer_Repository {
	
	@Query(value = "select t from Ticket_Table t where t.id=:id")
	Ticket_Table viewTicketById(long id);

}