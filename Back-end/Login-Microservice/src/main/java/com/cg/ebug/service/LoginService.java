package com.cg.ebug.service;

import com.cg.ebug.entity.EmployeeTable;

public interface LoginService {

	

	public EmployeeTable addUser(EmployeeTable user);

	public EmployeeTable getUserByEmailIdAndPassword(String tempEmailId, String tempPassword);

}
