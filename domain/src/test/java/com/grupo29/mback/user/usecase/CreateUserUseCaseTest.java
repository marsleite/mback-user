package com.grupo29.mback.user.usecase;

import com.grupo29.mback.user.entities.Address;
import com.grupo29.mback.user.entities.User;
import com.grupo29.mback.user.entities.UserRole;
import com.grupo29.mback.user.exception.UserException;
import com.grupo29.mback.user.gateway.UserRepositoryGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreateUserUseCaseTest {

    private CreateUserUseCase createUserUseCase;
    private UserRepositoryGateway userRepositoryGateway;

    @BeforeEach
    public void setUp() {
        userRepositoryGateway = mock(UserRepositoryGateway.class);
        createUserUseCase = new CreateUserUseCase(userRepositoryGateway);
    }

    @Test
    public void testExecute_SuccessfulCreation() throws UserException {
        User user = User.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password")
                .roles(UserRole.VENDOR)
                .address(Address.builder()
                        .street("123 Main St")
                        .city("Springfield")
                        .state("IL")
                        .cep("12345")
                        .build())
                .build();

        when(userRepositoryGateway.getUserById(1L)).thenReturn(null);
        when(userRepositoryGateway.createUser(user)).thenReturn(user);

        User createdUser = createUserUseCase.execute(user);
        assertNotNull(createdUser);
        assertEquals(user, createdUser);

        verify(userRepositoryGateway, times(1)).getUserById(1L);
        verify(userRepositoryGateway, times(1)).createUser(user);
    }

    @Test
    public void testExecute_UserAlreadyExists() {
        User user = User.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password")
                .roles(UserRole.VENDOR)
                .address(Address.builder()
                        .street("123 Main St")
                        .city("Springfield")
                        .state("IL")
                        .cep("12345")
                        .build())
                .build();

        when(userRepositoryGateway.getUserById(1L)).thenReturn(user);

        assertThrows(UserException.class, () -> createUserUseCase.execute(user));
        verify(userRepositoryGateway, times(1)).getUserById(1L);
        verify(userRepositoryGateway, never()).createUser(user);
    }
}