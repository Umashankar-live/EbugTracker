package com.cg.ebug.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.ebug.entity.EmployeeTable;
import com.cg.ebug.dao.EmployeeDao;
import com.cg.ebug.exception.EbugException;
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
	public EmployeeTable addUser(EmployeeTable user) {
		try {
			List<EmployeeTable> EmployeeList = userDao.findAll();
			for (EmployeeTable isExists : EmployeeList) {
				if (isExists.getEmailId().equalsIgnoreCase(user.getEmailId())) {
					throw new EbugException("Customer already Exists");
				}
			}
			user.setRole("customer");
			return userDao.save(user);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
		
	}

	/***
	 * This function is used to delete user
	 */
	@Override
	public void deleteUser(Long userId) {
		EmployeeTable user=this.userDao.findAll().stream().filter(x -> userId.equals(x.getEmpId())).findAny().orElse(null);
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
	public EmployeeTable searchUser(Long userId) {
		EmployeeTable user = this.userDao.findAll().stream().filter(x -> userId.equals(x.getEmpId())).findAny().orElse(null);
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
	public EmployeeTable updateUser(EmployeeTable user) {
        if(this.userDao.save(user) == null)
			throw new NotPossibleException("Cannot update this user...");
		return this.userDao.save(user);
	}

	/***
	 * This function is used to get all user 
	 */
	@Override
	public List<EmployeeTable> getAllUser() {
		if(this.userDao.getEmployeeList() == null) {
			 logger.warn("Check if database is empty or not");
			 throw new NoValueFoundException("There is no user in Table...");
		}
		
		
		return this.userDao.getEmployeeList() ;
	}

}
