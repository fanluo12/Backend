package com.university.hw.repository;

import com.university.hw.domain.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCustomRepo {
    Student insert(Student student);
}
