package com.cg.ebug.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ebug.dao.Ticket_Repository;
import com.cg.ebug.entity.Ticket_Table;
import com.cg.ebug.exception.TicketNotFoundException;

@Service
@Transactional
public class CustomerService implements ICustomerService {
	
	@Autowired
	private Ticket_Repository ticketRepository;
	
	@Override
	public Ticket_Table createTicket(Ticket_Table ticket) {
		
		try {
			return ticketRepository.save(ticket);
		}catch(Exception ex) {
			System.out.println(ex);
			return null;
		}
		
	}
		
	
	@Override
	public List<Ticket_Table> getTicketByCriticalLevelIdByCus(Long criticalId,Long employeeId) 
	{
			List<Ticket_Table> ticketList = ticketRepository.FindTicketByCriticalLevelIdByEmp(criticalId,employeeId);
			if(ticketList.size() == 0) {
				throw new TicketNotFoundException("There is no ticket available for the critical level id: "+criticalId, "200");
			} else {
				return ticketList;
			}
	}
	
	
	@Override
	public List<Ticket_Table> getTicketByStatusByCus(Long statusId,Long employeeId)
	{
			List<Ticket_Table> ticketList = ticketRepository.FindTicketByStatusIdByEmp(statusId,employeeId);
			if(ticketList.size() == 0) {
				throw new TicketNotFoundException("There is no ticket available for the status id: "+statusId, "400");
			} else {
				return ticketList;
			}
	}


	@Override
	public Ticket_Table viewTicketById(Long id) {
		try {
			Optional<Ticket_Table> data = ticketRepository.findTicketById(id);
			if(data.isPresent())
				return data.get();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
		return null;
	}



}
