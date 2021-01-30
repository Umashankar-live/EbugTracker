package com.cg.ebug.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Project_Table {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long projectId;

	private String projectName;

	private String projectDescription;

	private String frontendTechno;

	private String backendTechno;

	private String projectManager;

	public Project_Table() {
		super();
		// TODO Auto-generated constructor stub
	}

	
    
	



	public Project_Table(Long projectId, String projectName, String projectDescription, String frontendTechno,
			String backendTechno, String projectManager) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.frontendTechno = frontendTechno;
		this.backendTechno = backendTechno;
		this.projectManager = projectManager;
	}



	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
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

}
