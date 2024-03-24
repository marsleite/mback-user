package com.grupo29.mback.user.usecase;

import com.grupo29.mback.user.gateway.UserRepositoryGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class DeleteUserUseCaseTest {

    private DeleteUserUseCase deleteUserUseCase;
    private UserRepositoryGateway userRepositoryGateway;

    @BeforeEach
    public void setUp() {
        userRepositoryGateway = mock(UserRepositoryGateway.class);
        deleteUserUseCase = new DeleteUserUseCase(userRepositoryGateway);
    }

    @Test
    public void testExecute_SuccessfulDeletion() {
        Long userId = 1L;
        deleteUserUseCase.execute(userId);

        verify(userRepositoryGateway, times(1)).deleteUserById(userId);
    }
}