package com.cg.ebug.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Project_Table {
	
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;

private String projectName;

private String projectDescription;

private String projectId;

private String frontendTechno;

private String backendTechno;

private String createdByAdminId;


private String projectManager;


public Project_Table() {
	super();
	// TODO Auto-generated constructor stub
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getProjectName() {
	return projectName;
}

public void setProjectName(String projectName) {
	this.projectName = projectName;
}

public String getProjectDescription() {
	return projectDescription;
}

public void setProjectDescription(String projectDescription) {
	this.projectDescription = projectDescription;
}

public String getProjectId() {
	return projectId;
}

public void setProjectId(String projectId) {
	this.projectId = projectId;
}

public String getFrontendTechno() {
	return frontendTechno;
}

public void setFrontendTechno(String frontendTechno) {
	this.frontendTechno = frontendTechno;
}

public String getBackendTechno() {
	return backendTechno;
}

public void setBackendTechno(String backendTechno) {
	this.backendTechno = backendTechno;
}

public String getProjectManager() {
	return projectManager;
}

public void setProjectManager(String projectManager) {
	this.projectManager = projectManager;
}

public Project_Table(Long id, String projectName, String projectDescription, String projectId, String frontendTechno,
		String backendTechno, String createdByAdminId, String projectManager) {
	super();
	this.id = id;
	this.projectName = projectName;
	this.projectDescription = projectDescription;
	this.projectId = projectId;
	this.frontendTechno = frontendTechno;
	this.backendTechno = backendTechno;
	this.createdByAdminId = createdByAdminId;
	this.projectManager = projectManager;
}

public String getCreatedByAdminId() {
	return createdByAdminId;
}

public void setCreatedByAdminId(String createdByAdminId) {
	this.createdByAdminId = createdByAdminId;
}


}
