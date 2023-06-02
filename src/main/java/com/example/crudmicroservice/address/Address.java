package com.example.crudmicroservice.address;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;
@Entity
public class Address {

    private @Id @GeneratedValue UUID id;
    private String address;

    public Address(){}
    public Address(String address){
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
