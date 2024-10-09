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

import com.ios.entity.Users;
import com.ios.exception.UserNotFoundException;
import com.ios.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/getuserbyid/{userId}")
	public Users getUser(@PathVariable("userId") int userId) {
		return userService.getUser(userId);
	}
	
	@PostMapping("/saveuser")
	public Users saveUser(@RequestBody Users user) {
		return userService.saveUser(user);
	}
	
	@DeleteMapping("/deleetuserbyid/{userId}")
	public ResponseEntity<String> deleteUserByIs(@PathVariable("userId") int userId) {
		return userService.deleteUserById(userId);
	}
	
	@PutMapping("/updateUser/{userId}")
	public Users updateUser(@RequestBody Users user,@PathVariable("userId") int userId) throws UserNotFoundException {
		return userService.updateUser(user,userId);
	}
	
	@GetMapping("/getallusers")
	public List<Users> getAllUsers() {
		return userService.getAllUsers();
	}
	
}
