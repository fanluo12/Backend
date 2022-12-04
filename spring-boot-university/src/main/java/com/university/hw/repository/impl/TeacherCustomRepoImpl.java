package com.university.hw.repository.impl;

import com.university.hw.domain.entity.Student;
import com.university.hw.domain.entity.Teacher;
import com.university.hw.repository.TeacherCustomRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class TeacherCustomRepoImpl implements TeacherCustomRepo {
    private final EntityManager entityManager;

    @Autowired
    public TeacherCustomRepoImpl (EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Teacher insert(Teacher teacher) {
        entityManager.persist(teacher);
        return teacher;
    }
}
