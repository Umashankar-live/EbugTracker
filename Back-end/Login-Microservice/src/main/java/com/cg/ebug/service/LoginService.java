package com.cg.ebug.service;

import com.cg.ebug.entity.Employee_Table;

public interface LoginService {

	

	public Employee_Table addUser(Employee_Table user);

	public Employee_Table getUserByEmailIdAndPassword(String tempEmailId, String tempPassword);

}
