package com.ios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ios.entity.User;
import com.ios.exception.UserNotFoundException;
import com.ios.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User getUser(int userId) {
		return userRepository.findById(userId).get();
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public ResponseEntity<String> deleteUserById(int userId) {
		userRepository.deleteById(userId);
		return new ResponseEntity<>("User Deleted..!", HttpStatus.NO_CONTENT);
	}

	public User updateUser(User user, int userId) throws UserNotFoundException {
		User foundUser = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User Not Found With USerId : " + userId));

		foundUser.setUerName(user.getUerName());
		foundUser.setEmail(user.getEmail());
		return userRepository.save(foundUser);

	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
