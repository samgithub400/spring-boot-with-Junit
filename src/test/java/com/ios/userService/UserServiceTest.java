package com.ios.userService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ios.entity.User;
import com.ios.repository.UserRepository;
import com.ios.service.UserService;

public class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@Mock
	private UserRepository userRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetUser() {
		Optional<User> user = Optional.ofNullable(new User(1, "user1", "user@gmail.com"));

		when(userRepository.findById(1)).thenReturn(user);

		User retrivedUser = userService.getUser(1);

		assertNotNull(retrivedUser);
		assertEquals("user1", retrivedUser.getUerName());
		assertEquals("user@gmail.com", retrivedUser.getEmail());
	}

	@Test
	public void tesGetAllUsers() {

		List<User> usersList = Arrays.asList(new User(1, "user1", "user1@gmail.com"),
				new User(2, "user2", "user2@gmail.com"), new User(1, "user3", "user3@gmail.com"));

		when(userRepository.findAll()).thenReturn(usersList);

		List<User> allUsers = userService.getAllUsers();

		assertNotNull(allUsers);
		assertEquals(3, allUsers.size());

	}
	
	@Test
	public void testSaveUser() {
		
		User user= new User(1, "user1", "user@gmail.com");
		
		when(userRepository.save(user)).thenReturn(user);
	
		User saveUser = userService.saveUser(user);
		
		assertNotNull(saveUser);
		assertEquals("user1", user.getUerName());
		assertEquals("user@gmail.com", user.getEmail());
		
	}

}
