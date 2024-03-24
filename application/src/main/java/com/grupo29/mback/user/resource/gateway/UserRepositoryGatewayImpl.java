package com.grupo29.mback.user.resource.gateway;

import com.grupo29.mback.user.entities.User;
import com.grupo29.mback.user.gateway.UserRepositoryGateway;
import com.grupo29.mback.user.resource.repository.entity.UserEntity;
import com.grupo29.mback.user.resource.repository.spring.UserRepositorySpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryGatewayImpl implements UserRepositoryGateway {

    private final UserRepositorySpring userRepositorySpring;

    @Autowired
    public UserRepositoryGatewayImpl(UserRepositorySpring userRepositorySpring) {
        this.userRepositorySpring = userRepositorySpring;
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = UserEntity.fromDomain(user);
        UserEntity savedUserEntity = userRepositorySpring.save(userEntity);
        return savedUserEntity.toDomain();
    }

    @Override
    public User getUserById(Long id) {
        return userRepositorySpring.findById(id).orElseThrow().toDomain();
    }

    @Override
    public User updateUser(User user) {
        return userRepositorySpring.save(UserEntity.fromDomain(user)).toDomain();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepositorySpring.deleteById(id);
    }
}
