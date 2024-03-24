package com.grupo29.mback.user.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        Address address = Address.builder()
                .id(1L)
                .street("123 Main St")
                .city("Springfield")
                .state("IL")
                .cep("12345")
                .build();

        user = User.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password123")
                .roles(List.of(new UserRole(1L, "VENDOR")))
                .address(address)
                .build();
    }

    @Test
    public void testGetters() {
        assertEquals(1L, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals(new UserRole(1L, "VENDOR"), user.getRoles().get(0));
        assertEquals("123 Main St", user.getAddress().getStreet());
        assertEquals("Springfield", user.getAddress().getCity());
        assertEquals("IL", user.getAddress().getState());
        assertEquals("12345", user.getAddress().getCep());
    }

    @Test
    public void testBuilder() {
        User newUser = User.builder()
                .id(2L)
                .name("Jane Smith")
                .email("jane.smith@example.com")
                .password("password456")
                .roles(List.of(new UserRole(1L, "ADMIN")))
                .address(null)
                .build();

        assertEquals(2L, newUser.getId());
        assertEquals("Jane Smith", newUser.getName());
        assertEquals("jane.smith@example.com", newUser.getEmail());
        assertEquals("password456", newUser.getPassword());
        assertEquals(new UserRole(1L, "ADMIN"), newUser.getRoles().get(0));
        assertNull(newUser.getAddress());
    }

    @Test
    public void testToString() {
        String expectedToString = "User(id=1, name=John Doe, email=john.doe@example.com, password=password123, roles=[UserRole(id=1, name=VENDOR)], address=Address(id=1, street=123 Main St, city=Springfield, state=IL, cep=12345))";
        assertEquals(expectedToString, user.toString());
    }
}