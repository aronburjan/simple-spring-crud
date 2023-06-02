package com.example.crudmicroservice.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProfileService {
    private final StudentRepository studentRepository;
    private final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    public ProfileService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student newStudent){
        logger.trace("ProfileService addStudent accessed");
        Student student = newStudent;
        if(!(emailIsUnique(studentRepository, student.getEmail())) || !checkEmailValidity(student.getEmail())){
            logger.error("Invalid email error");
            throw(new InvalidEmailException(student.getEmail()));
        }else{
            return studentRepository.save(student);
        }
    }

    public List<Student> getStudents(){
        logger.trace("ProfileService getStudents accessed");
        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }

    public Student getStudentById(UUID id){
        logger.trace("ProfileService get by id accessed");
        Student student = studentRepository.findById(id).orElseThrow(() -> new ProfileNotFoundException(id));
        return student;
    }

    public Student updateStudent(Student newStudent, UUID id){
        logger.trace("ProfileService update by id accessed");
        Student student = studentRepository.findById(id).orElseThrow(() -> new ProfileNotFoundException(id));
        Student updateStudent = newStudent;
        if(!(emailIsUnique(studentRepository, student.getEmail())) || !checkEmailValidity(student.getEmail())){
            logger.error("Invalid email error");
            throw(new InvalidEmailException(updateStudent.getEmail()));
        }else {
            student.setName(newStudent.getName());
            student.setEmail(newStudent.getEmail());
            return studentRepository.save(student);
        }
    }

    public void deleteStudent(UUID id){
        logger.trace("ProfileController DELETE by id accessed");
        studentRepository.deleteById(id);
    }

    public static boolean checkEmailValidity(String email){
        Pattern emailPattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = emailPattern.matcher(email);
        return mat.matches();
    }

    public static boolean emailIsUnique(StudentRepository studentRepository, String email){
        if(studentRepository.findByEmail(email).size() > 0){
            return false;
        }
        return true;
    }




}
