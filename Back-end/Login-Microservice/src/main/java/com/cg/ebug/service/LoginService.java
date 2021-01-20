package com.cg.ebug.service;

import com.cg.ebug.entity.User;

public interface LoginService {

	public User getUserByUserName(String userName);

	public User addUser(User user);

	public User getUserByUserNameAndPassword(String tempUsername, String tempPassword);

}
