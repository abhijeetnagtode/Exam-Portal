package com.exam.service;

import java.util.Set;

import com.exam.models.User;
import com.exam.models.UserRole;

public interface UserService {

	// creating user
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;

	// getting user by user name
	public User getUser(String username);

	// delete user

	public void deleteUser(Integer id);

	// authenticate user

	public User checkUser(String username, String password) throws Exception;

}
