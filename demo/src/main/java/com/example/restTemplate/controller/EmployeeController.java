package com.example.restTemplate.controller;

import com.example.restTemplate.entity.Activity;
import com.example.restTemplate.entity.Employee;
import com.example.restTemplate.service.impl.ActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class EmployeeController {
    @Autowired
    private ActivityServiceImpl activityServiceIml;

    @Autowired
    public EmployeeController(ActivityServiceImpl activityServiceIml) {
        this.activityServiceIml = activityServiceIml;
    }

    @GetMapping
    public ResponseEntity<Activity> getAll(){
        return new ResponseEntity<Activity>(activityServiceIml.getAll(), HttpStatus.OK);
//        return activityServiceIml.getAll();
    }

    @ExceptionHandler
    public ResponseEntity<String> exceptionHandler() {
        return new ResponseEntity<>("Exception", HttpStatus.BAD_REQUEST);
    }

}
