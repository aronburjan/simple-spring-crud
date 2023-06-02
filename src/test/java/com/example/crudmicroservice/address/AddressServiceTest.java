package com.example.crudmicroservice.address;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTest {
    @Autowired
    private AddressService addressService;

    @Test
    public void testGetAddress(){
        Address a1 = addressService.getAddress();
        assertEquals("London, Example street 1.", a1.getAddress());
    }
}
