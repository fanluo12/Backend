package com.example.university.service;

import com.example.university.domain.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final RestTemplate restTemplate;

    @Value("${university-endpoint}")
    private String url;

    @Autowired
    public StudentServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public StudentDTO[] getAllStudents() {
        StudentDTO[] stu = restTemplate.getForObject(url, StudentDTO[].class);
        return stu;
    }

    @Override
    public StudentDTO findById(String id) {
        StudentDTO studentDTO = null;
        StudentDTO[] stu = restTemplate.getForObject(url, StudentDTO[].class);
        for (StudentDTO s: stu) {
            if (s.getId().equals(id)) {
                studentDTO = s;
            }
        }
        return studentDTO;
    }
}
