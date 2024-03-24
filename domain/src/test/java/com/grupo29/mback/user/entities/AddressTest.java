package com.grupo29.mback.user.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressTest {

    private Address address;

    @BeforeEach
    public void setUp() {
        address = Address.builder()
                .id(1L)
                .street("123 Main St")
                .city("Springfield")
                .state("IL")
                .cep("12345")
                .build();
    }

    @Test
    public void testGetters() {
        assertEquals(1L, address.getId());
        assertEquals("123 Main St", address.getStreet());
        assertEquals("Springfield", address.getCity());
        assertEquals("IL", address.getState());
        assertEquals("12345", address.getCep());
    }

    @Test
    public void testBuilder() {
        Address newAddress = Address.builder()
                .id(2L)
                .street("456 Oak St")
                .city("Chicago")
                .state("IL")
                .cep("67890")
                .build();

        assertEquals(2L, newAddress.getId());
        assertEquals("456 Oak St", newAddress.getStreet());
        assertEquals("Chicago", newAddress.getCity());
        assertEquals("IL", newAddress.getState());
        assertEquals("67890", newAddress.getCep());
    }

    @Test
    public void testToString() {
        String expectedToString = "Address(id=1, street=123 Main St, city=Springfield, state=IL, cep=12345)";
        assertEquals(expectedToString, address.toString());
    }
}