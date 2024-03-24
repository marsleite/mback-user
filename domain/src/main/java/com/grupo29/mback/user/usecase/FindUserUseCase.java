package com.grupo29.mback.user.usecase;

import com.grupo29.mback.user.entities.User;
import com.grupo29.mback.user.exception.UserException;
import com.grupo29.mback.user.gateway.UserRepositoryGateway;

public class FindUserUseCase {
    private final UserRepositoryGateway userRepositoryGateway;


    public FindUserUseCase(UserRepositoryGateway userRepositoryGateway) {
        this.userRepositoryGateway = userRepositoryGateway;
    }

    public User execute(Long id) throws UserException {
        User user = userRepositoryGateway.getUserById(id);
        if (user == null) {
            throw new UserException("User not found!");
        }
        return user;
    }
}
