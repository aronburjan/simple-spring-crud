package com.example.crudmicroservice.address;

import java.util.UUID;

public class AddressNotFoundException extends RuntimeException {
    AddressNotFoundException(UUID id) {
        super("Address with id ["+ id.toString() +"] not found!");
    }
}
