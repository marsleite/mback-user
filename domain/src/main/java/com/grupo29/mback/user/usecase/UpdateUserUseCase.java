package com.grupo29.mback.user.usecase;

import com.grupo29.mback.user.entities.User;
import com.grupo29.mback.user.exception.UserException;
import com.grupo29.mback.user.gateway.UserRepositoryGateway;

public class UpdateUserUseCase {

    private final UserRepositoryGateway userRepositoryGateway;


    public UpdateUserUseCase(UserRepositoryGateway userRepositoryGateway) {
        this.userRepositoryGateway = userRepositoryGateway;
    }

    public User execute(User user) throws UserException {
        User userToBeUpdated = userRepositoryGateway.getUserById(user.getId());
        if (userToBeUpdated == null) {
            throw new UserException("User with id" + user.getId() +
                    " to be updated not found!");
        }
        return userRepositoryGateway.updateUser(user);
    }
}
