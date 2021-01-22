package com.cg.ebug.service;

import com.cg.ebug.entity.Employee_Table;

public interface LoginService {

	public Employee_Table getUserByUserName(String userName);

	public Employee_Table addUser(Employee_Table user);

	public Employee_Table getUserByUserNameAndPassword(String tempUsername, String tempPassword);

}
