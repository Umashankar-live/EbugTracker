package com.cg.ebug.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@Entity
@Table(name = "ticket_table")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TicketTable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private long custId;
	
	private String title;
	
	private String description;
	
	private String solution;
	
	private String name;

	private String type;

	@Column(name = "picByte", length = 65536, updatable = false)
	private byte[] picByte;
	
	private String status;
	
	private String criticalLevel;
	
	private Boolean isResolved;
	
	private long assignedToEmployee;
	
	private String employeeName;
	
	private Boolean isAssigned; 
	
	private String projectName;

	public TicketTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public TicketTable(Long id, long custId, String title, String description, String solution, String name,
			String type, byte[] picByte, String status, String criticalLevel, Boolean isResolved,
			long assignedToEmployee, String employeeName, Boolean isAssigned, String projectName) {
		super();
		this.id = id;
		this.custId = custId;
		this.title = title;
		this.description = description;
		this.solution = solution;
		this.name = name;
		this.type = type;
		this.picByte = picByte;
		this.status = status;
		this.criticalLevel = criticalLevel;
		this.isResolved = isResolved;
		this.assignedToEmployee = assignedToEmployee;
		this.employeeName = employeeName;
		this.isAssigned = isAssigned;
		this.projectName = projectName;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCriticalLevel() {
		return criticalLevel;
	}

	public void setCriticalLevel(String criticalLevel) {
		this.criticalLevel = criticalLevel;
	}

	public Boolean getIsResolved() {
		return isResolved;
	}

	public void setIsResolved(Boolean isResolved) {
		this.isResolved = isResolved;
	}

	public long getAssignedToEmployee() {
		return assignedToEmployee;
	}

	public void setAssignedToEmployee(long assignedToEmployee) {
		this.assignedToEmployee = assignedToEmployee;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Boolean getIsAssigned() {
		return isAssigned;
	}

	public void setIsAssigned(Boolean isAssigned) {
		this.isAssigned = isAssigned;
	}
	

}