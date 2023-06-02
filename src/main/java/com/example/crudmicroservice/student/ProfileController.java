package com.example.crudmicroservice.student;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class ProfileController {
    private final StudentRepository studentRepository;

    public ProfileController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping("/students")
    Student newStudent(@RequestBody Student newStudent) {
        Pattern emailPattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Student student = newStudent;
        Matcher mat = emailPattern.matcher(student.getEmail());
        if(studentRepository.findByEmail(student.getEmail()).size() > 0 || !(mat.matches())){
            throw(new InvalidEmailException(student.getEmail()));
        }else{
            return studentRepository.save(student);
        }
    }

    @GetMapping("/students")
    List<Student> getStudents() {
        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }

    @GetMapping("/students/{id}")
    Student getStudentById(@PathVariable UUID id){
        Student student = studentRepository.findById(id).orElseThrow(() -> new ProfileNotFoundException(id));
        return student;
    }

    @PutMapping("/students/{id}")
    Student updateStudent(@RequestBody Student newStudent, @PathVariable UUID id){
        Pattern emailPattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Student student = studentRepository.findById(id).orElseThrow(() -> new ProfileNotFoundException(id));
        Student updateStudent = newStudent;
        Matcher mat = emailPattern.matcher(updateStudent.getEmail());
        if(studentRepository.findByEmail(updateStudent.getEmail()).size() > 0 || !(mat.matches())){
            throw(new InvalidEmailException(updateStudent.getEmail()));
        }else {
            student.setName(newStudent.getName());
            student.setEmail(newStudent.getEmail());
            return studentRepository.save(student);
        }
    }

    @DeleteMapping("/students/{id}")
    void deleteStudent(@PathVariable UUID id){
        studentRepository.deleteById(id);
    }
}

