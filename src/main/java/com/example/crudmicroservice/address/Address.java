package com.example.crudmicroservice.address;
import java.util.UUID;
public class Address {
    UUID id;
    String address;

    public UUID getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
