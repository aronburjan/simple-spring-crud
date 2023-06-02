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
        String UUIDString = "ab05532a-bca0-4e4f-9452-aa18493aacb9";
        Student s1 = this.studentRepository.getById(UUID.fromString(UUIDString));
        assertNotNull(s1);
        assertEquals(UUID.fromString(UUIDString),s1.getId());
        assertEquals("Anthony",s1.getName());
    }

    @Test
    public void deleteStudentById() throws Exception{
        String UUIDString = "819f8a8c-a1be-42a2-a672-c0b57001094e";
        this.studentRepository.deleteById(UUID.fromString(UUIDString));
        assertThrows(JpaObjectRetrievalFailureException.class, () -> this.studentRepository.getReferenceById(UUID.fromString(UUIDString)));
    }


}