package com.example.crudmicroservice.student;

import jakarta.persistence.Entity;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;
    @Test
    @Rollback(false)
    public void addStudentToDatabase() throws Exception {
        Student s1 = new Student("Anthony", "anthony@soprano.com");
        assertNull(s1.getId());
        this.studentRepository.save(s1);
        assertNotNull(s1.getId());
    }


    @Test
    public void getStudentById() throws Exception{
        String UUIDString = "ab05532a-bca0-4e4f-9452-aa18493aacb9";
        Student s1 = this.studentRepository.getById(UUID.fromString(UUIDString));
        assertNotNull(s1);
        assertEquals(UUID.fromString(UUIDString),s1.getId());
        assertEquals("Anthony",s1.getName());
    }


}