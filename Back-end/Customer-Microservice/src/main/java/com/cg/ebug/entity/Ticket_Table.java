package com.cg.ebug.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonAutoDetect;


@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Ticket_Table {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String title;
	
	private String comments;
	
	private String solution;
	
	private String fileName;
	
	private Boolean isUpdatedByAdmin;
	
	private Boolean isVerifiedByAdmin;
	
	private Boolean isResolved;
	
	private Long assignedToEmployee;
	
	@Column
	private long custId;

	//Bidirectional MApping, here fk is generate with status_id field
	 @ManyToOne //(fetch = FetchType.LAZY)
     @JoinColumn(name = "status_id")
//	 @JsonBackReference(value="statusTable")
	 private Status_Table statusTable;
	 
	// Bidirectional Mapping, here fk is generate with critical_id field
	 @ManyToOne
   @JoinColumn(name = "critical_id")
//	 @JsonBackReference(value="criticalLevel")
	 private CriticalLevel_Table criticalLevel;
	 
	 @ManyToOne
	 @JoinColumn(name = "project_id")
//	 @JsonBackReference(value="projectTable")
	 private Project_Table projectTable;
	 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Boolean getIsUpdatedByAdmin() {
		return isUpdatedByAdmin;
	}

	public void setIsUpdatedByAdmin(Boolean isUpdatedByAdmin) {
		this.isUpdatedByAdmin = isUpdatedByAdmin;
	}

	public Boolean getIsVerifiedByAdmin() {
		return isVerifiedByAdmin;
	}

	public void setIsVerifiedByAdmin(Boolean isVerifiedByAdmin) {
		this.isVerifiedByAdmin = isVerifiedByAdmin;
	}

	public Boolean getIsResolved() {
		return isResolved;
	}

	public void setIsResolved(Boolean isResolved) {
		this.isResolved = isResolved;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public Status_Table getStatusTable() {
		return statusTable;
	}

	public void setStatusTable(Status_Table statusTable) {
		this.statusTable = statusTable;
	}

	public CriticalLevel_Table getCriticalLevel() {
		return criticalLevel;
	}

	public void setCriticalLevel(CriticalLevel_Table criticalLevel) {
		this.criticalLevel = criticalLevel;
	}

	public Project_Table getProjectTable() {
		return projectTable;
	}

	public void setProjectTable(Project_Table projectTable) {
		this.projectTable = projectTable;
	}

	public Ticket_Table() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getAssignedToEmployee() {
		return assignedToEmployee;
	}

	public void setAssignedToEmployee(Long assignedToEmployee) {
		this.assignedToEmployee = assignedToEmployee;
	}

	public Ticket_Table(Long id, String title, String comments, String solution, String fileName,
			Boolean isUpdatedByAdmin, Boolean isVerifiedByAdmin, Boolean isResolved, Long assignedToEmployee,
			long custId, Status_Table statusTable, CriticalLevel_Table criticalLevel, Project_Table projectTable) {
		super();
		this.id = id;
		this.title = title;
		this.comments = comments;
		this.solution = solution;
		this.fileName = fileName;
		this.isUpdatedByAdmin = isUpdatedByAdmin;
		this.isVerifiedByAdmin = isVerifiedByAdmin;
		this.isResolved = isResolved;
		this.assignedToEmployee = assignedToEmployee;
		this.custId = custId;
		this.statusTable = statusTable;
		this.criticalLevel = criticalLevel;
		this.projectTable = projectTable;
	}

	
	


	
	 
	 
	 	
}
