package com.example.crudmicroservice.student;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
public class ProfileController {
    private final StudentRepository studentRepository;

    public ProfileController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping("/students")
    Student newStudent(@RequestBody Student newStudent) {
        return studentRepository.save(newStudent);
    }

    @GetMapping("/students")
    List<Student> getStudents() {
        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }

    @GetMapping("/students/{id}")
    Student getStudentById(@PathVariable UUID id){
        Student student = studentRepository.findById(id).orElseThrow(() -> new ProfileNotFoundException(id)); //usernotfoundexception
        return student;
    }

    @PutMapping("/students/{id}")
    Student updateStudent(@RequestBody Student newStudent, @PathVariable UUID id){
        Student student = studentRepository.findById(id).orElseThrow(() -> new ProfileNotFoundException(id));
        student.setName(newStudent.getName());
        student.setEmail(newStudent.getName());
        return studentRepository.save(student);
    }

    @DeleteMapping("/students/{id}")
    void deleteStudent(@PathVariable UUID id){
        studentRepository.deleteById(id);
    }
}

