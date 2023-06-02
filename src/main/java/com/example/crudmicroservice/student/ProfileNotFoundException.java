package com.example.crudmicroservice.student;

import java.util.UUID;

public class ProfileNotFoundException extends RuntimeException {
    ProfileNotFoundException(UUID id) {
        super("Profile with id ["+ id.toString() +"] not found!");
    }
}
