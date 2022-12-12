package com.example.search.service;

import com.example.search.domain.StudentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface StudentService {
//    Map<String, Map[]> fetchStudentsById(List<String> ids);
    Map fetchAllStudents();
    Map fetchAllTeachers();
    Map getStudentByIds(List<String> ids);
}
