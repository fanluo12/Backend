package com.example.university.service;

import com.example.university.domain.TeacherDTO;
import org.springframework.stereotype.Service;

@Service
public interface TeacherService {
    TeacherDTO[] getAllTeachers();
}
