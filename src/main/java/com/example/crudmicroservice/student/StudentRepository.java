package com.example.crudmicroservice.student;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface StudentRepository extends JpaRepository<Student, UUID>{
}
