package com.university.hw.service;

//import com.university.hw.domain.CommonResponse;
import com.university.hw.domain.entity.Student;
import com.university.hw.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public interface StudentService {
    //    CommonResponse findById(String id);
//    CommonResponse findAll();
//    CommonResponse insert(Student stu);

    Optional<Student> findById(String id);

    List<Student> findAll();

    Student insert(@RequestBody Student student);

//    List<Student> getStudents();
}
