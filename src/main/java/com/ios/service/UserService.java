package com.ios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ios.entity.Users;
import com.ios.exception.UserNotFoundException;
import com.ios.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Users getUser(int userId) {
		return userRepository.findById(userId).get();
	}

	public Users saveUser(Users user) {
		return userRepository.save(user);
	}

	public ResponseEntity<String> deleteUserById(int userId) throws UserNotFoundException {
		if (!userRepository.existsById(userId)) {
			throw new UserNotFoundException("User Not Found With USerId : " + userId);
		}
		userRepository.deleteById(userId);
		return new ResponseEntity<>("User Deleted..!", HttpStatus.NO_CONTENT);
	}

<<<<<<< Updated upstream
<<<<<<< Updated upstream
	public User updateUser(User user, int userId) throws UserNotFoundException {
<<<<<<< HEAD
		User foundUser = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User Not Found With USerId : " + userId));
=======
=======
>>>>>>> Stashed changes
	public Users updateUser(Users user, int userId) throws UserNotFoundException {
		if(!userRepository.existsById(userId)) {
			throw new UserNotFoundException("User Not Found With USerId : " + userId);
		}				
>>>>>>> Stashed changes
=======
		if(!userRepository.existsById(userId)) {
			throw new UserNotFoundException("User Not Found With USerId : " + userId);
		}				
>>>>>>> 8fa7f4369403d3fd464d38d17eb824e3653d2985

		user.setUserId(userId);
		return userRepository.save(user);

	}

	public List<Users> getAllUsers() {
		return userRepository.findAll();
	}

}
