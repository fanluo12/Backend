package com.example.demo.repository.impl;

import com.example.demo.domain.entity.Student;
import com.example.demo.repository.StudentCustomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class StudentCustomRepoImpl implements StudentCustomRepo {
    private final EntityManager entityManager;

    @Autowired
    public StudentCustomRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Student insert(Student student) {
        entityManager.persist(student);
        return student;
    }

    @Override
    public Student update(Student student) {
        entityManager.merge(student);
        return student;
    }
}
