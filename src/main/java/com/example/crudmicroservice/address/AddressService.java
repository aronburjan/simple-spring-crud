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

    public Address getAddressById(UUID id){
        logger.trace("AddressService get by Id accessed");
        Address address = addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException(id));
        return address;
    }
}
