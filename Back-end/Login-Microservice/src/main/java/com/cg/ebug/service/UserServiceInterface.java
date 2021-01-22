package com.cg.ebug.service;

import java.util.List;

import com.cg.ebug.entity.Employee_Table;

public interface UserServiceInterface {

	public Employee_Table addUser(Employee_Table user);

	public void deleteUser(Long userId);

	public Employee_Table searchUser(Long userId);
	
	public List<Employee_Table> getAllUser();

	public Employee_Table updateUser(Employee_Table user);

}
