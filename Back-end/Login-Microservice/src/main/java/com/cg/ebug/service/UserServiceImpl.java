package com.cg.ebug.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.ebug.entity.Employee_Table;
import com.cg.ebug.dao.EmployeeDao;
import com.cg.ebug.exception.NoValueFoundException;
import com.cg.ebug.exception.NotPossibleException;

@Service
public class UserServiceImpl implements UserServiceInterface {

	@Autowired
	private EmployeeDao userDao;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	/***
	 * This Function is used to add User
	 */
	@Override
	public Employee_Table addUser(Employee_Table user) {
		return this.userDao.save(user);
		
	}

	/***
	 * This function is used to delete user
	 */
	@Override
	public void deleteUser(Long userId) {
		Employee_Table user=this.userDao.findAll().stream().filter(x -> userId.equals(x.getEmpId())).findAny().orElse(null);
		if(user==null) {
			logger.warn("check the userId is correct or not");
			throw new NotPossibleException("Given ID is not present so User Deletion operation is not possible...");
		}
		else
		this.userDao.deleteById(userId);

	}

	/***
	 * This Function is used to search the user
	 */
	@Override
	public Employee_Table searchUser(Long userId) {
		Employee_Table user = this.userDao.findAll().stream().filter(x -> userId.equals(x.getEmpId())).findAny().orElse(null);
		if(user==null) {
			logger.warn("check the userId is correct or not");
			throw new NoValueFoundException("No such User is available...");
		}
		else
		return user;
	}

	/***
	 * This Function is used to update the user
	 */
	@Override
	public Employee_Table updateUser(Employee_Table user) {
        if(this.userDao.save(user) == null)
			throw new NotPossibleException("Cannot update this user...");
		return this.userDao.save(user);
	}

	/***
	 * This function is used to get all user 
	 */
	@Override
	public List<Employee_Table> getAllUser() {
		if(this.userDao.getEmployeeList() == null) {
			 logger.warn("Check if database is empty or not");
			 throw new NoValueFoundException("There is no user in Table...");
		}
		
		
		return this.userDao.getEmployeeList() ;
	}

}
