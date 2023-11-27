package com.example.demo.student;

import com.example.demo.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @GetMapping(path = "{studentId}")
    public Optional<Student> getStudent(@PathVariable("studentId") Long studentId){
        return studentService.getStudent(studentId);
    }

    @PostMapping
    public Student registerStudent(@RequestBody Student student){
        return studentService.registerStudent(student);
    }

    @PutMapping(path = "{studentId}")
    public Student updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ){
        return studentService.updateStudent(studentId, name, email);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }
}
