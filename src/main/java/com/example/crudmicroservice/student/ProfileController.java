package com.example.crudmicroservice.student;


import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/students")
    Student newStudent(@RequestBody Student newStudent) {
        return profileService.addStudent(newStudent);
    }

    @GetMapping("/students")
    List<Student> getStudents() {
        return profileService.getStudents();
    }

    @GetMapping("/students/{id}")
    Student getStudentById(@PathVariable UUID id){
        return profileService.getStudentById(id);
    }

    @PutMapping("/students/{id}")
    Student updateStudent(@RequestBody Student newStudent, @PathVariable UUID id){
        return profileService.updateStudent(newStudent, id);
    }

    @DeleteMapping("/students/{id}")
    void deleteStudent(@PathVariable UUID id){
        profileService.deleteStudent(id);
    }
}

