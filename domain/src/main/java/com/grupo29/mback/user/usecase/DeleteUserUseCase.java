package com.grupo29.mback.user.usecase;

import com.grupo29.mback.user.gateway.UserRepositoryGateway;

public class DeleteUserUseCase {

    private final UserRepositoryGateway userRepositoryGateway;

    public DeleteUserUseCase(UserRepositoryGateway userRepositoryGateway) {
        this.userRepositoryGateway = userRepositoryGateway;
    }

    public void execute(Long id) {
        userRepositoryGateway.deleteUserById(id);
    }
}
