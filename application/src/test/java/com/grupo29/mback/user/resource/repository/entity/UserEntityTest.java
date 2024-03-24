package com.grupo29.mback.user.resource.repository.entity;

import com.grupo29.mback.user.entities.Address;
import com.grupo29.mback.user.entities.User;
import com.grupo29.mback.user.entities.UserType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserEntityTest {

    @Test
    public void testFromDomain() {
        // Create a User
        User user = User.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password")
                .userType(UserType.VENDOR)
                .address(Address.builder()
                        .id(1L)
                        .street("123 Main St")
                        .city("Springfield")
                        .state("IL")
                        .cep("12345")
                        .build())
                .build();

        // Convert User to UserEntity
        UserEntity userEntity = UserEntity.fromDomain(user);

        // Verify conversion
        assertNotNull(userEntity);
        assertEquals(user.getId(), userEntity.getId());
        assertEquals(user.getName(), userEntity.getName());
        assertEquals(user.getEmail(), userEntity.getEmail());
        assertEquals(user.getPassword(), userEntity.getPassword());
        assertEquals(user.getUserType(), userEntity.getUserType());
        assertNotNull(userEntity.getAddress());
        assertEquals(user.getAddress().getId(), userEntity.getAddress().getId());
        assertEquals(user.getAddress().getStreet(), userEntity.getAddress().getStreet());
        assertEquals(user.getAddress().getCity(), userEntity.getAddress().getCity());
        assertEquals(user.getAddress().getState(), userEntity.getAddress().getState());
        assertEquals(user.getAddress().getCep(), userEntity.getAddress().getCep());
    }

    @Test
    public void testToDomain() {
        // Create a UserEntity
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password")
                .userType(UserType.VENDOR)
                .address(AddressEntity.builder()
                        .id(1L)
                        .street("123 Main St")
                        .city("Springfield")
                        .state("IL")
                        .cep("12345")
                        .build())
                .build();

        // Convert UserEntity to User
        User user = userEntity.toDomain();

        // Verify conversion
        assertNotNull(user);
        assertEquals(userEntity.getId(), user.getId());
        assertEquals(userEntity.getName(), user.getName());
        assertEquals(userEntity.getEmail(), user.getEmail());
        assertEquals(userEntity.getPassword(), user.getPassword());
        assertEquals(userEntity.getUserType(), user.getUserType());
        assertNotNull(user.getAddress());
        assertEquals(userEntity.getAddress().getId(), user.getAddress().getId());
        assertEquals(userEntity.getAddress().getStreet(), user.getAddress().getStreet());
        assertEquals(userEntity.getAddress().getCity(), user.getAddress().getCity());
        assertEquals(userEntity.getAddress().getState(), user.getAddress().getState());
        assertEquals(userEntity.getAddress().getCep(), user.getAddress().getCep());
    }
}
