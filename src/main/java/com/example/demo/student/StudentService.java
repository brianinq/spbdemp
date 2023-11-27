package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Optional<Student> getStudent(Long studentId){
        return studentRepository.findById(studentId);
    }

    public Student registerStudent(Student student) {
        Optional<Student> student1 = studentRepository.findByEmail(student.getEmail());
        if(student1.isPresent()) {
            throw new IllegalStateException("Email already registered");
        }else{
            return studentRepository.save(student);
        }
    }

    public void deleteStudent(Long studentId) {
        if(studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
        }else {
            throw new IllegalStateException("Record not found");
        }
    }

    @Transactional
    public Student updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()->new IllegalStateException("Record does not exist"));
        if(name != null && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }
        if(email != null && !Objects.equals(student.getEmail(), name)){
            student.setEmail(email);
        }
        return student;
    }
}
