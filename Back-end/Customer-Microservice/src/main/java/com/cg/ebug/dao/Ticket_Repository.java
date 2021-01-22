package com.cg.ebug.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.ebug.entity.Ticket_Table;

public interface Ticket_Repository extends JpaRepository<Ticket_Table, Long> {

	@Query(value = "Select * from TICKET_TABLE a where a.status_id=:id and a.custId=:id",  nativeQuery = true)
	public List<Ticket_Table> FindTicketByStatusId( @Param("id") Long id);
	
	@Query(value = "Select * from TICKET_TABLE a where a.critical_id=:id",  nativeQuery = true)
	public List<Ticket_Table> FindTicketByCriticalLevelId( @Param("id") Long id);
	
	@Query(value = "Select * from TICKET_TABLE a where a.project_id=:id",  nativeQuery = true)
	public List<Ticket_Table> FindTicketByProjectId( @Param("id") Long id);
	
	Optional<Ticket_Table> findTicketById(Long id);
	
	// employee
	@Query(value = "Select * from TICKET_TABLE a where a.status_id=:id AND a.assigned_To_Employee=:employeeId", nativeQuery = true)
	public List<Ticket_Table> FindTicketByStatusIdByEmp( @Param("id") Long statusId, @Param("employeeId") Long employeeId);
	
	@Query(value = "Select * from TICKET_TABLE a where a.critical_id=:id AND a.assigned_To_Employee=:employeeId", nativeQuery = true)
	public List<Ticket_Table> FindTicketByCriticalLevelIdByEmp( @Param("id") Long criticalId, @Param("employeeId") Long employeeId);
	
	@Query(value = "Select * from TICKET_TABLE a where a.project_id=:projectId AND a.assigned_To_Employee=:employeeId", nativeQuery = true)
	public List<Ticket_Table> FindTicketByProjectIdByEmp( @Param("projectId") Long projectId, @Param("employeeId") Long employeeId);

	// customer
	@Query(value = "Select * from TICKET_TABLE a where a.status_id=:id AND a.cust_Id=:cusId", nativeQuery = true)
	public List<Ticket_Table> FindTicketByStatusIdByCus( @Param("id") Long statusId, @Param("cusId") Long cusId);

	@Query(value = "Select * from TICKET_TABLE a where a.critical_id=:id AND a.cust_Id=:cusId", nativeQuery = true)
	public List<Ticket_Table> FindTicketByCriticalLevelIdByCus( @Param("id") Long criticalId, @Param("cusId") Long cusId);
}
