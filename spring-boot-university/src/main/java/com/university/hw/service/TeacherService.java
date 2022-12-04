package com.university.hw.service;

import com.university.hw.domain.entity.Student;
import com.university.hw.domain.entity.Teacher;
import com.university.hw.repository.StudentRepository;
import com.university.hw.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public interface TeacherService {
    //    CommonResponse findById(String id);
//    CommonResponse findAll();
//    CommonResponse insert(Student stu);
    Optional<Teacher> findById(String id);

    List<Teacher> findAll();

    Teacher insert(@RequestBody Teacher teacher);
}
