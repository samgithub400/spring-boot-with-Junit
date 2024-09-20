package com.ios.userService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.ios.entity.User;
import com.ios.exception.UserNotFoundException;
import com.ios.repository.UserRepository;
import com.ios.service.UserService;


public class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@Mock
	private UserRepository userRepository;
	
	private User exstingUser;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		exstingUser= new User();
		exstingUser.setUserId(1);
		exstingUser.setUerName("user1");
		exstingUser.setEmail("user1@gmail.com");
	}

	@Test
	// @Disabled
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

		User user = new User(1, "user1", "user@gmail.com");

		when(userRepository.save(user)).thenReturn(user);

		User saveUser = userService.saveUser(user);

		assertNotNull(saveUser);
		assertEquals("user1", user.getUerName());
		assertEquals("user@gmail.com", user.getEmail());

	}

	@Test
	public void testDeleteUserById() throws UserNotFoundException {
		when(userRepository.existsById(1)).thenReturn(true);

		userService.deleteUserById(1);

		verify(userRepository, times(1)).deleteById(1);

	}

	@Test
	public void testDeleteUserByIdThrowsException() {

		when(userRepository.existsById(1)).thenReturn(false);

		UserNotFoundException exception = assertThrows(UserNotFoundException.class,
				() -> userService.deleteUserById(1));
		assertEquals("User Not Found With USerId : " + 1, exception.getMessage());

		verify(userRepository, never()).deleteById(1);
	}
	
	@Test
	public void testUpdateUser() throws UserNotFoundException {
		
		User userDetails = new User();
		userDetails.setUerName("username1");
		userDetails.setEmail("user1@gmail.com");
		
		when(userRepository.existsById(1)).thenReturn(true);
		when(userRepository.save(any(User.class))).thenReturn(userDetails);
		
		User updateUser = userService.updateUser(userDetails, 1);
		assertEquals("username1", updateUser.getUerName());
		assertEquals(1, updateUser.getUserId());
		
		verify(userRepository,times(1)).save(any(User.class));
		
		
	}

}
