package com.grupo29.mback.user.resource.gateway;

import com.grupo29.mback.user.entities.User;
import com.grupo29.mback.user.gateway.UserRepositoryGateway;
import com.grupo29.mback.user.resource.repository.entity.AddressEntity;
import com.grupo29.mback.user.resource.repository.entity.UserEntity;
import com.grupo29.mback.user.resource.repository.entity.UserRoleEntity;
import com.grupo29.mback.user.resource.repository.spring.AddressRepositorySpring;
import com.grupo29.mback.user.resource.repository.spring.UserRepositorySpring;
import com.grupo29.mback.user.resource.repository.spring.UserRoleRepositorySpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserRepositoryGatewayImpl implements UserRepositoryGateway {

    private final UserRepositorySpring userRepositorySpring;
    private final AddressRepositorySpring addressRepositorySpring;
    private final UserRoleRepositorySpring userRoleRepositorySpring;


    @Autowired
    public UserRepositoryGatewayImpl(UserRepositorySpring userRepositorySpring, AddressRepositorySpring addressRepositorySpring, UserRoleRepositorySpring userRoleRepositorySpring) {
        this.userRepositorySpring = userRepositorySpring;
        this.addressRepositorySpring = addressRepositorySpring;
        this.userRoleRepositorySpring = userRoleRepositorySpring;
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = UserEntity.fromDomain(user);
        UserEntity savedUserEntity = userRepositorySpring.save(userEntity);
        addressRepositorySpring.save(userEntity.getAddress());
        userRoleRepositorySpring.saveAll(userEntity.getRoles());
        return savedUserEntity.toDomain();
    }

    @Override
    public User getUserById(Long id) {
        return userRepositorySpring.findById(id).orElseThrow().toDomain();
    }

    @Override
    public User updateUser(User user) {
        addressRepositorySpring.save(AddressEntity.fromDomain(user.getAddress()));
        userRoleRepositorySpring.saveAll(user.getRoles().stream().map(UserRoleEntity::fromDomain).collect(Collectors.toList()));
        return userRepositorySpring.save(UserEntity.fromDomain(user)).toDomain();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepositorySpring.deleteById(id);
    }
}
