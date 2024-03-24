package com.grupo29.mback.user.usecase;

import com.grupo29.mback.user.entities.User;
import com.grupo29.mback.user.exception.UserException;
import com.grupo29.mback.user.gateway.UserRepositoryGateway;

public class CreateUserUseCase {

    private final UserRepositoryGateway userRepositoryGateway;

    public CreateUserUseCase(UserRepositoryGateway userRepositoryGateway) {
        this.userRepositoryGateway = userRepositoryGateway;
    }

    public User execute(User user) throws UserException {
        if (!userExists(user)) {
            return userRepositoryGateway.createUser(user);
        } else {
            throw new UserException("User with " + user.getId() + " already exists");
        }
    }

    private boolean userExists(User user) {
        return userRepositoryGateway.getUserById(user.getId()) != null;
    }
}
