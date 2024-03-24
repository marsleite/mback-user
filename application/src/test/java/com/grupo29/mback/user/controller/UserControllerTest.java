package com.grupo29.mback.user.controller;

import com.grupo29.mback.user.controller.UserController;
import com.grupo29.mback.user.entities.User;
import com.grupo29.mback.user.exception.UserException;
import com.grupo29.mback.user.usecase.CreateUserUseCase;
import com.grupo29.mback.user.usecase.DeleteUserUseCase;
import com.grupo29.mback.user.usecase.FindUserUseCase;
import com.grupo29.mback.user.usecase.UpdateUserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private CreateUserUseCase createUserUseCase;

    @Mock
    private UpdateUserUseCase updateUserUseCase;

    @Mock
    private FindUserUseCase findUserUseCase;

    @Mock
    private DeleteUserUseCase deleteUserUseCase;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserById_UserFound() throws UserException {
        // Prepare test data
        Long userId = 1L;
        User user = User.builder().id(userId).name("John Doe").build();
        when(findUserUseCase.execute(userId)).thenReturn(user);

        // Test getUserById
        ResponseEntity<User> response = userController.getUserById(userId);

        // Verify response
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testGetUserById_UserNotFound() throws UserException {
        // Prepare test data
        Long userId = 1L;
        when(findUserUseCase.execute(userId)).thenThrow(new UserException("User not found"));

        // Test getUserById
        ResponseEntity<User> response = userController.getUserById(userId);

        // Verify response
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testCreateUser() throws UserException {
        // Prepare test data
        User user = User.builder().name("John Doe").build();
        when(createUserUseCase.execute(user)).thenReturn(user);

        // Test createUser
        ResponseEntity<User> response = userController.createUser(user);

        // Verify response
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testCreateUser_Exception() throws UserException {
        // Prepare test data
        User user = User.builder().name("John Doe").build();
        when(createUserUseCase.execute(user)).thenThrow(new UserException("Invalid user data"));

        // Test createUser
        ResponseEntity<User> response = userController.createUser(user);

        // Verify response
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testUpdateUser() throws UserException {
        // Prepare test data
        Long userId = 1L;
        User userDetails = User.builder().name("Jane Doe").build();
        User updatedUser = User.builder().id(userId).name("Jane Doe").build();
        when(updateUserUseCase.execute(userId, userDetails)).thenReturn(updatedUser);

        // Test updateUser
        ResponseEntity<User> response = userController.updateUser(userId, userDetails);

        // Verify response
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUser, response.getBody());
    }

    @Test
    public void testUpdateUser_Exception() throws UserException {
        // Prepare test data
        Long userId = 1L;
        User userDetails = User.builder().name("Jane Doe").build();
        when(updateUserUseCase.execute(userId, userDetails)).thenThrow(new UserException("User not found"));

        // Test updateUser
        ResponseEntity<User> response = userController.updateUser(userId, userDetails);

        // Verify response
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testDeleteUser() {
        // Test deleteUser
        ResponseEntity<?> response = userController.deleteUser(1L);

        // Verify response
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }
}
