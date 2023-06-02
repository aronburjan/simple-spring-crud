package com.example.crudmicroservice.student;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProfileServiceTest {
    @Autowired
    private ProfileService profileService;

    @Test
    public void testAddStudent(){
        Student s1 = new Student("Saul Goodman", "saul.goodman@gmail.com");
        assertNull(s1.getId());
        profileService.addStudent(s1);
        assertNotNull(s1.getId());
    }

    @Test
    public void testAddStudentWithInvalidEmailFormat(){
        Student s1 = new Student("Lalo Salamanca", "lalosalamancgmail.com");
        assertThrows(InvalidEmailException.class, ()-> profileService.addStudent(s1));
    }

    @Test
    @Sql(statements = "INSERT INTO Student (Id, name, email) VALUES (UUID(), 'Walter White', 'walter.white@gmail.com')",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testAddStudentWithEmailAlreadyInUse(){
        Student s1 = new Student("Fake Walter White", "walter.white@gmail.com");
        assertThrows(InvalidEmailException.class, ()-> profileService.addStudent(s1));
    }

    @Test
    public void testGetStudentById(){
        Student s1 = new Student("Gustavo Fring", "gus.fring@pollos.com");
        assertNull(s1.getId());
        profileService.addStudent(s1);
        assertNotNull(s1.getId());
        assertEquals(s1.getName(), profileService.getStudentById(s1.getId()).getName());
    }

    @Test
    public void testUpdateStudent(){
        Student s1 = new Student("Hank Schrader", "hank.schrader@gmail.com");
        Student update = new Student("ASAC Schrader", "asac.schrader@gmail.com");
        assertNull(s1.getId());
        profileService.addStudent(s1);
        assertNotNull(s1.getId());
        Student response = profileService.updateStudent(update, s1.getId());
        assertEquals("ASAC Schrader", response.getName());
    }

    @Test
    public void testDeleteStudent(){
        Student s1 = new Student("Skinny Pete", "skinny.pete@gmail.com");
        assertNull(s1.getId());
        profileService.addStudent(s1);
        assertNotNull(s1.getId());
        profileService.deleteStudent(s1.getId());
        assertThrows(ProfileNotFoundException.class, () -> profileService.getStudentById(s1.getId()));
    }
}
