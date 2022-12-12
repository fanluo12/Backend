package com.example.university.controller;

import com.example.university.domain.StudentDTO;
import com.example.university.domain.TeacherDTO;
import com.example.university.exceptions.ResourceNotFoundException;
import com.example.university.service.StudentService;
import com.example.university.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/university/teachers")
@Slf4j
public class TeacherController {
    private final TeacherService teacherService;

    @Value("${server.port}")
    private int randomServerPort;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public TeacherDTO[] findAll() {
        return teacherService.getAllTeachers();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleNotFound() {
        log.info("Resources not found here");
        return new ResponseEntity("Resources not found", HttpStatus.NOT_FOUND);
    }
}
