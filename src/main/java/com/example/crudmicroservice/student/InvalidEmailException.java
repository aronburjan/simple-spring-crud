package com.example.crudmicroservice.student;
public class InvalidEmailException extends RuntimeException{
    InvalidEmailException(String email) {
        super(email + " is invalid format, or is already in use!");
    }
}
