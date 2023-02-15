package com.exam.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.exceptionhandler.NoRecordFoundException;
import com.exam.models.Role;
import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;

	// creating user
	@PostMapping("/save")
	public User createUser(@RequestBody User user) throws Exception {

		user.setProfile("defalult.png");
		Set<UserRole> roles = new HashSet<>();
		Role role = new Role();
		role.setRoleId(46);
		role.setRoleName("normal");

		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		roles.add(userRole);

		return this.userService.createUser(user, roles);
	}

	// get user by username
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		return this.userService.getUser(username);
	}

	// delete user by id
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") Integer id) {
		this.userService.deleteUser(id);
	}

	// validte user

	@GetMapping("/{usercheck}")
	public ResponseEntity<User> checkUser(@RequestBody User user) throws Exception {
		String userName = user.getUsername();
		String userPassword = user.getPassword();
		if (userName == userPassword) {
			user = userService.checkUser(userName, userPassword);
			System.out.println("sucess login");
			return new ResponseEntity<User>(user,HttpStatus.FOUND);
		}
		else {
			System.out.println("login unsuccessful");
			throw new NoRecordFoundException("no record found");
		}
	}
}
