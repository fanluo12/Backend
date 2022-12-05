package com.university.hw.controller;

import com.university.hw.domain.CommonResponse;
import com.university.hw.domain.entity.Student;
import com.university.hw.domain.entity.Teacher;
import com.university.hw.service.StudentService;
import com.university.hw.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public Optional<Student> findStuById(@PathVariable String id) {
        return studentService.findById(id);
    }

    @GetMapping
    public List<Student> findAll() {
        return studentService.findAll();
    }

    //path: /student, method: post ,  create student
    @PostMapping
    public Student insert(@RequestBody Student student) {
        return studentService.insert(student);
    }

    //TODO: /{id} , method : put,  update student
    @PutMapping("/{id}/{name}")
    public Student updateStudent(@PathVariable String id,
                                 @PathVariable(required = false) String name) {
        return studentService.update(id, name);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CommonResponse> handleNotFound() {
        return new ResponseEntity<>(
                new CommonResponse(-1, new Date(), "resource not found"),
                HttpStatus.NOT_FOUND
        );
    }
}
