package com.exam.serviceImpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.exceptionhandler.NoRecordFoundException;
import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.repository.RoleRepository;
import com.exam.repository.UserRepository;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	// creating user
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {

		User local = this.userRepository.findByUsername(user.getUsername());
		if (local != null) {
			System.out.println("User is alreday here");
			throw new Exception("User alreday present ");
		} else {

			// user create
			for (UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());

			}
			user.getUserRoles().addAll(userRoles);
			local = this.userRepository.save(user);
		}

		return local;
	}

	// getting user by username
	@Override
	public User getUser(String username) {
		return this.userRepository.findByUsername(username);
	}

	// delete user by id
	@Override
	public void deleteUser(Integer id) {
		this.userRepository.deleteById(id);

	}

	// checking username and pass
	@Override
	public User checkUser(String username, String password) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new NoRecordFoundException("invalid user");
		} else {
			if (user.getUsername().equals(username) == user.getPassword().equals(password))
				return user;

		}
		throw new NoRecordFoundException("you have incorrect username and passowrd");

	}

}
