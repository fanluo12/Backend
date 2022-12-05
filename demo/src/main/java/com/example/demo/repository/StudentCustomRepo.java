package com.example.demo.repository;

import com.example.demo.domain.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCustomRepo {
    Student insert(Student student);
    Student update(Student student);
}
