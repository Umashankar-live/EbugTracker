package com.cg.ebug.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ebug.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	User findByUserName(String userName);

	User findByUserNameAndPassword(String tempUsername, String tempPassword);

}
