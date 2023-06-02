package com.example.crudmicroservice.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    public ProfileController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping("/students")
    Student newStudent(@RequestBody Student newStudent) {
        logger.trace("ProfileController POST accessed");
        Pattern emailPattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Student student = newStudent;
        Matcher mat = emailPattern.matcher(student.getEmail());
        if(studentRepository.findByEmail(student.getEmail()).size() > 0 || !(mat.matches())){
            logger.error("Invalid email error");
            throw(new InvalidEmailException(student.getEmail()));
        }else{
            return studentRepository.save(student);
        }
    }

    @GetMapping("/students")
    List<Student> getStudents() {
        logger.trace("ProfileController GET accessed");
        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }

    @GetMapping("/students/{id}")
    Student getStudentById(@PathVariable UUID id){
        logger.trace("ProfileController GET by id accessed");
        Student student = studentRepository.findById(id).orElseThrow(() -> new ProfileNotFoundException(id));
        return student;
    }

    @PutMapping("/students/{id}")
    Student updateStudent(@RequestBody Student newStudent, @PathVariable UUID id){
        logger.trace("ProfileController PUT accessed");
        Pattern emailPattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Student student = studentRepository.findById(id).orElseThrow(() -> new ProfileNotFoundException(id));
        Student updateStudent = newStudent;
        Matcher mat = emailPattern.matcher(updateStudent.getEmail());
        if(studentRepository.findByEmail(updateStudent.getEmail()).size() > 0 || !(mat.matches())){
            logger.error("Invalid email error");
            throw(new InvalidEmailException(updateStudent.getEmail()));
        }else {
            student.setName(newStudent.getName());
            student.setEmail(newStudent.getEmail());
            return studentRepository.save(student);
        }
    }

    @DeleteMapping("/students/{id}")
    void deleteStudent(@PathVariable UUID id){
        logger.trace("ProfileController DELETE by id accessed");
        studentRepository.deleteById(id);
    }
}

