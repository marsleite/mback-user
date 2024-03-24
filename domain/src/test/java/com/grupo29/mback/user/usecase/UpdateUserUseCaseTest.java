package com.grupo29.mback.user.usecase;

import com.grupo29.mback.user.entities.Address;
import com.grupo29.mback.user.entities.User;
import com.grupo29.mback.user.entities.UserRole;
import com.grupo29.mback.user.exception.UserException;
import com.grupo29.mback.user.gateway.UserRepositoryGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UpdateUserUseCaseTest {

    private UpdateUserUseCase updateUserUseCase;
    private UserRepositoryGateway userRepositoryGateway;

    @BeforeEach
    public void setUp() {
        userRepositoryGateway = mock(UserRepositoryGateway.class);
        updateUserUseCase = new UpdateUserUseCase(userRepositoryGateway);
    }

    @Test
    public void testExecute_UserFound() throws UserException {
        Long userId = 1L;
        User existingUser = User.builder()
                .id(userId)
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password")
                .roles(List.of(new UserRole(2L, "VENDOR")))
                .address(Address.builder()
                        .street("123 Main St")
                        .city("Springfield")
                        .state("IL")
                        .cep("12345")
                        .build())
                .build();

        User updatedUser = User.builder()
                .id(userId)
                .name("Jane Smith")
                .email("jane.smith@example.com")
                .password("newpassword")
                .roles(List.of(new UserRole(1L, "ADMIN")))
                .address(Address.builder()
                        .street("456 Oak St")
                        .city("Chicago")
                        .state("IL")
                        .cep("67890")
                        .build())
                .build();

        when(userRepositoryGateway.getUserById(userId)).thenReturn(existingUser);
        when(userRepositoryGateway.updateUser(updatedUser)).thenReturn(updatedUser);

        User returnedUser = updateUserUseCase.execute(userId, updatedUser);

        assertNotNull(returnedUser);
        assertEquals(updatedUser, returnedUser);

        verify(userRepositoryGateway, times(1)).getUserById(userId);
        verify(userRepositoryGateway, times(1)).updateUser(updatedUser);
    }

    @Test
    public void testExecute_UserNotFound() {
        Long userId = 1L;
        User updatedUser = User.builder()
                .id(userId)
                .name("Jane Smith")
                .email("jane.smith@example.com")
                .password("newpassword")
                .roles(List.of(new UserRole(1L, "ADMIN")))
                .address(Address.builder()
                        .street("456 Oak St")
                        .city("Chicago")
                        .state("IL")
                        .cep("67890")
                        .build())
                .build();

        when(userRepositoryGateway.getUserById(userId)).thenReturn(null);

        assertThrows(UserException.class, () -> updateUserUseCase.execute(userId, updatedUser));

        verify(userRepositoryGateway, times(1)).getUserById(userId);
        verify(userRepositoryGateway, never()).updateUser(updatedUser);
    }
}