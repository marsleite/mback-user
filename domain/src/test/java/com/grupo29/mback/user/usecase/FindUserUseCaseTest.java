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

public class FindUserUseCaseTest {

    private FindUserUseCase findUserUseCase;
    private UserRepositoryGateway userRepositoryGateway;

    @BeforeEach
    public void setUp() {
        userRepositoryGateway = mock(UserRepositoryGateway.class);
        findUserUseCase = new FindUserUseCase(userRepositoryGateway);
    }

    @Test
    public void testExecute_UserFound() throws UserException {
        Long userId = 1L;
        User user = User.builder()
                .id(userId)
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

        when(userRepositoryGateway.getUserById(userId)).thenReturn(user);

        User foundUser = findUserUseCase.execute(userId);

        assertNotNull(foundUser);
        assertEquals(user, foundUser);

        verify(userRepositoryGateway, times(1)).getUserById(userId);
    }

    @Test
    public void testExecute_UserNotFound() {
        Long userId = 1L;

        when(userRepositoryGateway.getUserById(userId)).thenReturn(null);

        assertThrows(UserException.class, () -> findUserUseCase.execute(userId));

        verify(userRepositoryGateway, times(1)).getUserById(userId);
    }
}