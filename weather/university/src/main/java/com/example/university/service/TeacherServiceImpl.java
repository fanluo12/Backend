package com.example.university.service;

import com.example.university.domain.StudentDTO;
import com.example.university.domain.TeacherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TeacherServiceImpl implements TeacherService{
    private final RestTemplate restTemplate;

    @Value("${teacher-endpoint}")
    private String url;

    @Autowired
    public TeacherServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public TeacherDTO[] getAllTeachers() {
        TeacherDTO[] tea = restTemplate.getForObject(url, TeacherDTO[].class);
        return tea;
    }
}
