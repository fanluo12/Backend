package com.example.search.controller;

import com.example.search.domain.StudentDTO;
import com.example.search.service.EmployeeService;
import com.example.search.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class SearchController {

    private final EmployeeService employeeService;
    private final StudentService studentService;

    public SearchController(EmployeeService employeeService, StudentService studentService) {
        this.employeeService = employeeService;
        this.studentService = studentService;
    }

    @GetMapping("/search/employees")
    public ResponseEntity<?> getDetails(@RequestParam List<Integer> ages) {
        //TODO
        return new ResponseEntity<>(employeeService.fetchAllEmployeesByAges(ages), HttpStatus.OK);
    }

    @GetMapping("/search/students")
    public ResponseEntity<?> getAllStudents() {
        log.info("Fetch all students");
        return new ResponseEntity<>(studentService.fetchAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/search/teachers")
    public ResponseEntity<?> getAllTeachers() {
        log.info("Fetch all teachers");
        return new ResponseEntity<>(studentService.fetchAllTeachers(), HttpStatus.OK);
    }

    @GetMapping("/search/studentbyids")
    public ResponseEntity<?> getStudentsById(@RequestParam List<String> ids) {
        log.info("Get student with id = " + ids);
        return new ResponseEntity<>(studentService.getStudentByIds(ids), HttpStatus.OK);
    }

    @GetMapping("/search/studentsandteachers")
    public void multithreadingSearch() throws Exception {
        Thread studentThread = new Thread(() -> {
            try {
                studentService.fetchAllStudents();
            } catch (Exception e) {
                System.out.println("Exception caught here when running student service: " + e);
            }
        });

        Thread teacherThread = new Thread(() -> {
            try {
                studentService.fetchAllTeachers();
            } catch (Exception e) {
                System.out.println("Exception caught here when running teacher service: " + e);
            }
        });

        studentThread.start();
        teacherThread.start();
        studentThread.join();
        teacherThread.join();

    }
}