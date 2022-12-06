package com.example.restTemplate.service;

import com.example.restTemplate.entity.Activity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    List<Activity> getAll();
}
