package com.example.demo.service;

import com.example.demo.domain.entity.CommonResponse;
import com.example.demo.domain.entity.Student;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public interface StudentService {
    CommonResponse findById(String id);
    CommonResponse findAll();
    CommonResponse insert(Student stu);

    @Transactional
    CommonResponse update(String id, String name);
}
