package com.cg.ebug.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ebug.entity.EmployeeTable;
import com.cg.ebug.dao.EmployeeDao;

@Service
public class LoginServiceImpl implements LoginService {

	// The process of injection spring bean dependencies while initializing it
	@Autowired
	private EmployeeDao userDao;


	

	@Override
	public EmployeeTable addUser(EmployeeTable user) {
		user.setRole("customer");
		return userDao.save(user);
	}

	@Override
	public EmployeeTable getUserByEmailIdAndPassword(String tempEmaiId, String tempPassword) {
		return userDao.findByEmailIdAndPassword(tempEmaiId,tempPassword);
	}

}
