package com.university.hw.controller;

import com.university.hw.domain.entity.Student;
import com.university.hw.domain.entity.Teacher;
import com.university.hw.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

//    @GetMapping
//    public List<Student> getStudents() {
//        return studentService.getStudents();
//    }

    @GetMapping("/{id}")
    public Optional<Student> findStuById(@PathVariable String id) {
        return studentService.findById(id);
    }

    @GetMapping("/student")
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @PostMapping
    public Student insert(@RequestBody Student student) {
        return studentService.insert(student);
    }
}
