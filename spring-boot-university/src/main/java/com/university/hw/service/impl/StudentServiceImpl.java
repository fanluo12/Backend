package com.university.hw.service.impl;

import com.university.hw.domain.entity.Student;
import com.university.hw.repository.StudentRepository;
import com.university.hw.repository.TeacherRepository;
import com.university.hw.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<Student> findById(String id) {
        Optional<Student> stu = studentRepository.findById(id);
        if (stu.isEmpty()) {
            throw new ResourceNotFoundException("...");
        }
        return stu;
    }

    @Override
    public List<Student> findAll() {
        List<Student> stuList = studentRepository.findAll();
        return stuList;
    }

    @Override
    @Transactional
    public Student insert(Student stu) {
        Student student = studentRepository.insert(stu);
        return student;
    }

//    @Override
//    public List<Student> getStudents() {
//        return studentRepository.findAll();
//    }
}
