package com.grupo29.mback.user.resource.repository.entity;

import com.grupo29.mback.user.entities.Address;
import com.grupo29.mback.user.entities.User;
import com.grupo29.mback.user.entities.UserRole;
import org.junit.jupiter.api.Test;

import java.util.List;

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
                .roles(List.of(new UserRole(1L, "VENDOR")))
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
                .roles(List.of(new UserRoleEntity(1L, "VENDOR")))
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
        assertNotNull(user.getAddress());
        assertEquals(userEntity.getAddress().getId(), user.getAddress().getId());
        assertEquals(userEntity.getAddress().getStreet(), user.getAddress().getStreet());
        assertEquals(userEntity.getAddress().getCity(), user.getAddress().getCity());
        assertEquals(userEntity.getAddress().getState(), user.getAddress().getState());
        assertEquals(userEntity.getAddress().getCep(), user.getAddress().getCep());
    }
}
