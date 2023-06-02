package com.example.crudmicroservice.address;

import com.example.crudmicroservice.student.ProfileNotFoundException;
import com.example.crudmicroservice.student.Student;
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

    @GetMapping("/addresses/{id}")
    Address getAddressById(@PathVariable UUID id){
        Address address = addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException(id));
        return address;
    }
}
