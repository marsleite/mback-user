package com.grupo29.mback.user.resource.gateway;

import com.grupo29.mback.user.entities.Address;
import com.grupo29.mback.user.entities.User;
import com.grupo29.mback.user.entities.UserType;
import com.grupo29.mback.user.resource.repository.entity.AddressEntity;
import com.grupo29.mback.user.resource.repository.entity.UserEntity;
import com.grupo29.mback.user.resource.repository.spring.AddressRepositorySpring;
import com.grupo29.mback.user.resource.repository.spring.UserRepositorySpring;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserRepositoryGatewayImplTest {

    @Mock
    private UserRepositorySpring userRepositorySpring;

    @Mock
    private AddressRepositorySpring addressRepositorySpring;

    @InjectMocks
    private UserRepositoryGatewayImpl userRepositoryGatewayImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        // Create a User
        User user = User.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password")
                .userType(UserType.VENDOR)
                .address(Address.builder()
                        .street("123 Main St")
                        .city("Springfield")
                        .state("IL")
                        .cep("12345")
                        .build())
                .build();

        // Mock behavior for userRepositorySpring.save
        UserEntity savedUserEntity = UserEntity.fromDomain(user);
        AddressEntity addressEntity = AddressEntity.fromDomain(user.getAddress());
        addressEntity.setId(1L); // Assuming an ID is generated during save
        savedUserEntity.setAddress(addressEntity);
        when(userRepositorySpring.save(any(UserEntity.class))).thenReturn(savedUserEntity);

        // Mock behavior for addressRepositorySpring.save
        when(addressRepositorySpring.save(any(AddressEntity.class))).thenReturn(addressEntity);

        // Test createUser method
        User savedUser = userRepositoryGatewayImpl.createUser(user);

        // Assertions
        assertNotNull(savedUser);
        assertEquals(user, savedUser);

        // Verify interactions with repositories
        verify(userRepositorySpring, times(1)).save(any(UserEntity.class));
        verify(addressRepositorySpring, times(1)).save(any(AddressEntity.class));
    }

    @Test
    public void testGetUserById_UserFound() {
        Long userId = 1L;
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        User expectedUser = User.builder()
                .id(userId)
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password")
                .userType(UserType.VENDOR)
                .address(Address.builder()
                        .street("123 Main St")
                        .city("Springfield")
                        .state("IL")
                        .cep("12345")
                        .build())
                .build();

        when(userRepositorySpring.findById(userId)).thenReturn(Optional.of(userEntity));

        User foundUser = userRepositoryGatewayImpl.getUserById(userId);

        assertNotNull(foundUser);
        assertEquals(expectedUser, foundUser);
    }

    @Test
    public void testGetUserById_UserNotFound() {
        Long userId = 1L;

        when(userRepositorySpring.findById(userId)).thenReturn(Optional.empty());

        User foundUser = userRepositoryGatewayImpl.getUserById(userId);

        assertNull(foundUser);
    }

    @Test
    public void testUpdateUser() {
        User user = User.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password")
                .userType(UserType.VENDOR)
                .address(Address.builder()
                        .street("123 Main St")
                        .city("Springfield")
                        .state("IL")
                        .cep("12345")
                        .build())
                .build();

        UserEntity updatedUserEntity = UserEntity.fromDomain(user);
        AddressEntity addressEntity = AddressEntity.fromDomain(user.getAddress());
        addressEntity.setId(1L); // Assuming an ID is generated during save
        updatedUserEntity.setAddress(addressEntity);

        when(addressRepositorySpring.save(any(AddressEntity.class))).thenReturn(addressEntity);
        when(userRepositorySpring.save(any(UserEntity.class))).thenReturn(updatedUserEntity);

        User updatedUser = userRepositoryGatewayImpl.updateUser(user);

        assertNotNull(updatedUser);
        assertEquals(user, updatedUser);
    }

    @Test
    public void testDeleteUserById() {
        Long userId = 1L;

        userRepositoryGatewayImpl.deleteUserById(userId);

        verify(userRepositorySpring, times(1)).deleteById(userId);
    }
}
