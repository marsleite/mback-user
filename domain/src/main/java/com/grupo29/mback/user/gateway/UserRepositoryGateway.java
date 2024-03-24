package com.grupo29.mback.user.gateway;

import com.grupo29.mback.user.entities.User;

public interface UserRepositoryGateway {
    User createUser(User user);

    User getUserById(Long id);

    User updateUser(User user);

    void deleteUserById(Long id);
}
