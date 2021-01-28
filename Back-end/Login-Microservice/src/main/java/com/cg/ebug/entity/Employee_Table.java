package com.cg.ebug.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee_Table {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long empId;
	@Column
	private String firstName;
	
	@Column
	private String lastName ;
	
	@Column
	private String projectName;
	
	@Column
	private String emailId;
	@Column
	private long mobileNo;
	@Column
	private String password;
	@Column
	private String role;
	
	
	
	public Employee_Table() {
		super();
	}



	public Employee_Table(long empId, String firstName, String lastName, String projectName, String emailId,
			long mobileNo, String password, String role) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.projectName = projectName;
		this.emailId = emailId;
		this.mobileNo = mobileNo;
		this.password = password;
		this.role = role;
	}



	public long getEmpId() {
		return empId;
	}



	public void setEmpId(long empId) {
		this.empId = empId;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getProjectName() {
		return projectName;
	}



	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}



	public String getEmailId() {
		return emailId;
	}



	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}



	public long getMobileNo() {
		return mobileNo;
	}



	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}

    

	
	

	
	
}
