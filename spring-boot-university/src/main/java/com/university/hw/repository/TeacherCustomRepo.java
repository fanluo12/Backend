package com.university.hw.repository;

import com.university.hw.domain.entity.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherCustomRepo {
    Teacher insert(Teacher teacher);
}
