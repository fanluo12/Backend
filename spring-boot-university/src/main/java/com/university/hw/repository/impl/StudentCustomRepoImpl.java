package com.university.hw.repository.impl;

import com.university.hw.domain.entity.Student;
import com.university.hw.repository.StudentCustomRepo;
import com.university.hw.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

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
}
