package com.example.crudmicroservice.student;

import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
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
    public void addStudentToDatabase() throws Exception {
        Student s1 = new Student("Saul Goodman", "saul.goodman@gmail.com");
        assertNull(s1.getId());
        this.studentRepository.save(s1);
        assertNotNull(s1.getId());
    }


    @Test
    public void getStudentById() throws Exception{
        Student s1 = new Student("Saul Goodman", "saul.goodman@gmail.com");
        this.studentRepository.save(s1);
        assertEquals(s1.getId(),this.studentRepository.getReferenceById(s1.getId()).getId());
        assertEquals("Saul Goodman",this.studentRepository.getReferenceById(s1.getId()).getName());
    }

    @Test
    public void deleteStudentById() throws Exception{
        Student s1 = new Student("Saul Goodman", "saul.goodman@gmail.com");
        this.studentRepository.save(s1);
        this.studentRepository.deleteById(s1.getId());
        assertThrows(JpaObjectRetrievalFailureException.class, () -> this.studentRepository.getReferenceById(s1.getId()));
    }


}