package com.university.hw.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teacher")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "teachers", fetch = FetchType.LAZY)
    private List<Student> students;
}
