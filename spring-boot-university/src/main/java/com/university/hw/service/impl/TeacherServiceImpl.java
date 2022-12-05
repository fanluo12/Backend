package com.university.hw.service.impl;

import com.university.hw.domain.entity.Student;
import com.university.hw.domain.entity.Teacher;
import com.university.hw.repository.StudentRepository;
import com.university.hw.repository.TeacherRepository;
import com.university.hw.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Optional<Teacher> findById(String id) {
        Optional<Teacher> tea = teacherRepository.findById(id);
        if (tea.isEmpty()) {
            throw new ResourceNotFoundException("...");
        }
        return tea;
    }

    @Override
    public List<Teacher> findAll() {
        List<Teacher> teaList = teacherRepository.findAll();
        return teaList;
    }

    @Override
    @Transactional
    public Teacher insert(Teacher tea) {
        Teacher teacher = teacherRepository.insert(tea);
        return teacher;
    }

}
