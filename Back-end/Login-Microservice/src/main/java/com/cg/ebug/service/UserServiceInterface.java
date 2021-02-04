package com.cg.ebug.service;

import java.util.List;

import com.cg.ebug.entity.EmployeeTable;

public interface UserServiceInterface {

	public EmployeeTable addUser(EmployeeTable user);

	public void deleteUser(Long userId);

	public EmployeeTable searchUser(Long userId);
	
	public List<EmployeeTable> getAllUser();

	public EmployeeTable updateUser(EmployeeTable user);

}
