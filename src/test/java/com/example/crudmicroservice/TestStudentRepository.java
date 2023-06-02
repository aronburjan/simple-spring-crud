package com.example.crudmicroservice;

import com.example.crudmicroservice.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface TestStudentRepository extends JpaRepository<Student, UUID> {
    List<Student> findByEmail(String email);
}
