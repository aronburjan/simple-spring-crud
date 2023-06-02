package com.example.crudmicroservice.address;

import com.example.crudmicroservice.student.ProfileController;
import com.example.crudmicroservice.student.ProfileNotFoundException;
import com.example.crudmicroservice.student.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class AddressController {
    private final AddressRepository addressRepository;

    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    private final Logger logger = LoggerFactory.getLogger(AddressController.class);

    @GetMapping("/addresses/{id}")
    Address getAddressById(@PathVariable UUID id){
        logger.trace("AddressController GET by Id accessed");
        Address address = addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException(id));
        return address;
    }
}
