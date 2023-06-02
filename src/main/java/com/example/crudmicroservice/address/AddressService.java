package com.example.crudmicroservice.address;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final Logger logger = LoggerFactory.getLogger(AddressService.class);

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address getAddress(){
        logger.trace("AddressService get by Id accessed");
        Address address = new Address();
        address.setId(UUID.randomUUID());
        address.setAddress("London, Example street 1.");
        return address;
    }
}
