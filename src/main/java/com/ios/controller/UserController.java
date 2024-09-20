package com.ios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ios.entity.User;
import com.ios.exception.UserNotFoundException;
import com.ios.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/getuserbyid/{userId}")
	public User getUser(@PathVariable("userId") int userId) {
		return userService.getUser(userId);
	}
	
	@PostMapping("/saveuser")
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@DeleteMapping("/deleetuserbyid/{userId}")
	public ResponseEntity<String> deleteUserByIs(@PathVariable("userId") int userId) throws UserNotFoundException {
		return userService.deleteUserById(userId);
	}
	
	@PutMapping("/updateUser/{userId}")
	public User updateUser(@RequestBody User user,@PathVariable("userId") int userId) throws UserNotFoundException {
		return userService.updateUser(user,userId);
	}
	
	@GetMapping("/getallusers")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
}
