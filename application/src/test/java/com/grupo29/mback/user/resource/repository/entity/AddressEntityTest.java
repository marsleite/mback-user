package com.grupo29.mback.user.resource.repository.entity;

import com.grupo29.mback.user.resource.repository.spring.AddressRepositorySpring;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AddressEntityTest {

    @Autowired
    private AddressRepositorySpring addressRepositorySpring;

    @Test
    public void testAddressEntityMapping() {
        // Create an AddressEntity
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreet("123 Main St");
        addressEntity.setCity("Springfield");
        addressEntity.setState("IL");
        addressEntity.setCep("12345");

        // Save AddressEntity to the in-memory database
        addressEntity = addressRepositorySpring.save(addressEntity);

        // Retrieve AddressEntity from the database
        AddressEntity retrievedAddressEntity = addressRepositorySpring.findById(addressEntity.getId()).orElse(null);
        assertNotNull(retrievedAddressEntity);

        // Verify that the retrieved AddressEntity matches the original one
        assertEquals("123 Main St", retrievedAddressEntity.getStreet());
        assertEquals("Springfield", retrievedAddressEntity.getCity());
        assertEquals("IL", retrievedAddressEntity.getState());
        assertEquals("12345", retrievedAddressEntity.getCep());

        // Clean up: delete the AddressEntity from the database
        addressRepositorySpring.delete(retrievedAddressEntity);

        // Verify that the AddressEntity is deleted
        assertFalse(addressRepositorySpring.existsById(retrievedAddressEntity.getId()));
    }
}
