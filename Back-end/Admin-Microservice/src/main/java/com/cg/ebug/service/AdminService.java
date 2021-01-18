package com.cg.ebug.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ebug.dao.CriticalLevel_Repository;
import com.cg.ebug.dao.Project_Repository;
import com.cg.ebug.dao.Status_Repository;
import com.cg.ebug.dao.Ticket_Repository;
import com.cg.ebug.entity.CriticalLevel_Table;
import com.cg.ebug.entity.Project_Table;
import com.cg.ebug.entity.Status_Table;
import com.cg.ebug.entity.Ticket_Table;

@Service
@Transactional
public class AdminService implements IAdminService{
	
	@Autowired
	private Ticket_Repository ticketRepository;
	@Autowired
	private Status_Repository statusRepository;
	@Autowired
	private CriticalLevel_Repository criticalRepository;
	@Autowired
	private Project_Repository projectRepository;
	

	@Override
	public List<Ticket_Table> getAllTickets() {
		// TODO Auto-generated method stub
		List<Ticket_Table> ticketList = ticketRepository.findAll();

		if(ticketList.size() == 0) {
			return null;
		} else {
			return ticketList;
		}
	}
	
	@Override
	public List<Ticket_Table> getTicketByStatusId(Long id) {
		// TODO Auto-generated method stub
		try {
			List<Ticket_Table> ticketList = ticketRepository.FindTicketByStatusId(id);
			if(ticketList.size() == 0) {
				return null;
			} else {
				return ticketList;
			}
		}catch(Exception ex) {
			System.out.println(ex);
			return null;
		}

	}

	@Override
	public Ticket_Table createTicket(Ticket_Table ticket) {
		// TODO Auto-generated method stub
		try {
			return ticketRepository.save(ticket);
		}catch(Exception ex) {
			System.out.println(ex);
			return null;
		}
		
	}
	
	
	@Override
	public List<Status_Table> getAllStatus() {
		// TODO Auto-generated method stub
		try {
			return statusRepository.findAll();
		}catch(Exception ex) {
			System.out.println(ex);
			return null;
		}
		
	}

	@Override
	public Status_Table addStatus(Status_Table status) {
		// TODO Auto-generated method stub
		return statusRepository.save(status);
	}
	
	@Override
	public CriticalLevel_Table addCriticalLevel(CriticalLevel_Table critical) {
		// TODO Auto-generated method stub
		return criticalRepository.save(critical);
	}

	@Override
	public Ticket_Table updateTicketByAdmin(Ticket_Table ticket) {
		try {
			ticket.setIsUpdatedByAdmin(true);
			return ticketRepository.save(ticket);
		}catch(Exception ex) {
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public List<CriticalLevel_Table> getAllCritical() {

		return null;
	}

	@Override
	public Project_Table addProject(Project_Table project) {
		// TODO Auto-generated method stub
		try {
			return projectRepository.save(project);
		}catch(Exception ex) {
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public List<Project_Table> getAllProject() {
		try {
			return projectRepository.findAll();
		}catch(Exception ex) {
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public Project_Table getProjectByID(Long id) {
		try {
			Optional<Project_Table> data = projectRepository.findById(id);
			if(data.isPresent())
			return data.get();
		}catch(Exception ex) {
			System.out.println(ex);
			return null;
		}
		return null;
	}

	@Override
	public List<Ticket_Table> getTicketByCriticalLevelId(Long id) {
		// TODO Auto-generated method stub
		try {
			List<Ticket_Table> ticketList = ticketRepository.FindTicketByCriticalLevelId(id);
			if(ticketList.size() == 0) {
				return null;
			} else {
				return ticketList;
			}
		}catch(Exception ex) {
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public List<Ticket_Table> getTicketByProjectId(Long id) {
		// TODO Auto-generated method stub
		try {
			List<Ticket_Table> ticketList = ticketRepository.FindTicketByProjectId(id);
			if(ticketList.size() == 0) {
				return null;
			} else {
				return ticketList;
			}
		}catch(Exception ex) {
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public Ticket_Table assignTicketToEmployeeByAdmin(Long ticketId, Long employeeId) {
		// TODO Auto-generated method stub
		
		try {
			Optional<Ticket_Table> data = ticketRepository.findById(ticketId);
			if(data.isPresent())
			{
				Ticket_Table ticket = data.get();
				ticket.setAssignedToEmployee(employeeId);
				return ticketRepository.save(ticket);
			}
			else {
				return null;
			}
		} catch(Exception ex) {
			System.out.println(ex);
			return null;
		}
		
	}

	@Override
	public Ticket_Table getTicketById(Long id) {
		// TODO Auto-generated method stub 
		try {
			Optional<Ticket_Table> data = ticketRepository.findById(id);
			if(data.isPresent())
			return data.get();
			else {
				return null;
			}
		}catch(Exception ex) {
			System.out.println(ex);
			return null;
		}
		
	}

	@Override
	public Ticket_Table updateTicketStatusById(Long id) {
		// TODO Auto-generated method stub 
		try {
			Optional<Ticket_Table> data = ticketRepository.findById(id);
			if(data.isPresent()) {
			data.get().setStatusTable(null);
			return data.get();
			}
			else {
				return null;
			}
		}catch(Exception ex) {
			System.out.println(ex);
			return null;
		}
		
	}

	
	 
	
}
