package com.example.crudmicroservice.address;

import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.jupiter.api.Assertions.*;
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressRepositoryTest {
    @Autowired
    private AddressRepository addressRepository;
    @Test
    public void addAddress() throws Exception {
        Address a1 = new Address("London, Example street 1.");
        assertNull(a1.getId());
        this.addressRepository.save(a1);
        assertNotNull(a1.getId());
    }


    @Test
    public void getAddressById() throws Exception{
        Address a1 = new Address("London, Example street 1.");
        this.addressRepository.save(a1);
        Address a2 = this.addressRepository.getById(a1.getId());
        assertEquals(a1.getId(),a2.getId());
        assertEquals("London, Example street 1.",a2.getAddress());
    }

    @Test
    public void deleteAddressById() throws Exception{
        Address a1 = new Address("London, Example street 1.");
        this.addressRepository.save(a1);
        this.addressRepository.deleteById(a1.getId());
        assertThrows(JpaObjectRetrievalFailureException.class, () -> this.addressRepository.getReferenceById(a1.getId()));
    }
}