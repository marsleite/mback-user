package com.grupo29.mback.user.resource.gateway;

import com.grupo29.mback.user.entities.Address;
import com.grupo29.mback.user.entities.User;
import com.grupo29.mback.user.entities.UserRole;
import com.grupo29.mback.user.resource.gateway.UserRepositoryGatewayImpl;
import com.grupo29.mback.user.resource.repository.entity.AddressEntity;
import com.grupo29.mback.user.resource.repository.entity.UserEntity;
import com.grupo29.mback.user.resource.repository.spring.AddressRepositorySpring;
import com.grupo29.mback.user.resource.repository.spring.UserRepositorySpring;
import com.grupo29.mback.user.resource.repository.spring.UserRoleRepositorySpring;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserRepositoryGatewayImplTest {

    @Mock
    private UserRepositorySpring userRepositorySpring;

    @Mock
    private AddressRepositorySpring addressRepositorySpring;

    @Mock
    private UserRoleRepositorySpring userRoleRepositorySpring;

    @InjectMocks
    private UserRepositoryGatewayImpl userRepositoryGateway;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        // Prepare test data
        User user = createUser();
        UserEntity userEntity = UserEntity.fromDomain(user);
        when(userRepositorySpring.save(any(UserEntity.class))).thenReturn(userEntity);

        // Test createUser
        User createdUser = userRepositoryGateway.createUser(user);

        // Verify
        assertNotNull(createdUser);
        assertEquals(user, createdUser);
        verify(userRepositorySpring, times(1)).save(any(UserEntity.class));
        verify(addressRepositorySpring, times(1)).save(any(AddressEntity.class));
        verify(userRoleRepositorySpring, times(1)).saveAll(anyIterable());
    }

    @Test
    public void testGetUserById_UserFound() {
        // Prepare test data
        Long userId = 1L;
        User user = createUser();
        UserEntity userEntity = UserEntity.fromDomain(user);
        when(userRepositorySpring.findById(userId)).thenReturn(Optional.of(userEntity));

        // Test getUserById
        User retrievedUser = userRepositoryGateway.getUserById(userId);

        // Verify
        assertNotNull(retrievedUser);
        assertEquals(user, retrievedUser);
        verify(userRepositorySpring, times(1)).findById(userId);
    }

    @Test
    public void testGetUserById_UserNotFound() {
        // Prepare test data
        Long userId = 1L;
        when(userRepositorySpring.findById(userId)).thenReturn(Optional.empty());

        // Test getUserById
        assertThrows(NoSuchElementException.class, () -> userRepositoryGateway.getUserById(userId));
        verify(userRepositorySpring, times(1)).findById(userId);
    }

    @Test
    public void testUpdateUser() {
        // Prepare test data
        User user = createUser();
        UserEntity userEntity = UserEntity.fromDomain(user);
        when(userRepositorySpring.save(any(UserEntity.class))).thenReturn(userEntity);

        // Test updateUser
        User updatedUser = userRepositoryGateway.updateUser(user);

        // Verify
        assertNotNull(updatedUser);
        assertEquals(user, updatedUser);
        verify(userRepositorySpring, times(1)).save(any(UserEntity.class));
        verify(addressRepositorySpring, times(1)).save(any(AddressEntity.class));
        verify(userRoleRepositorySpring, times(1)).saveAll(anyIterable());
    }

    @Test
    public void testDeleteUserById() {
        // Test deleteUserById
        userRepositoryGateway.deleteUserById(1L);
        verify(userRepositorySpring, times(1)).deleteById(1L);
    }

    // Helper method to create a sample user
    private User createUser() {
        return User.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password")
                .address(Address.builder().street("123 Main St").city("Springfield").state("IL").cep("12345").build())
                .roles(Collections.singletonList(UserRole.builder().id(1L).name("ROLE_USER").build()))
                .build();
    }
}
