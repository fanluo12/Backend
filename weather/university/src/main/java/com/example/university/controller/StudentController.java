package com.example.university.controller;

import com.example.university.domain.StudentDTO;
import com.example.university.exceptions.ResourceNotFoundException;
import com.example.university.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/university/students")
@Slf4j
public class StudentController {
    private final StudentService studentService;

    @Value("${server.port}")
    private int randomServerPort;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public StudentDTO[] findAll() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDTO findById(String id) {
        return studentService.findById(id);
    }

    @GetMapping("/university/port")
    public ResponseEntity<?> query() {
        return new ResponseEntity<>("University service + " + randomServerPort, HttpStatus.OK);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleNotFound() {
        log.info("Resources not found here");
        return new ResponseEntity("Resources not found", HttpStatus.NOT_FOUND);
    }
}
