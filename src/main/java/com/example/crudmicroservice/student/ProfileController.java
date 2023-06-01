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
    CollectionModel<EntityModel<Student>> listStudents() {
        List<EntityModel<Student>> studentList = studentRepository.findAll().stream().map((student -> EntityModel.of(student))).toList();
        return CollectionModel.of(studentList);
    }

    @GetMapping("/students/{id}")
    EntityModel<Student> getStudentById(@PathVariable UUID id){
        Student student = studentRepository.findById(id).orElse(null); //usernotfoundexception
        return EntityModel.of(student);
    }


}

