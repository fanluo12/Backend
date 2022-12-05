package com.university.hw.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "teacher_student",
            joinColumns = {
                @JoinColumn(name = "s_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "t_id", referencedColumnName = "id")
            }
    )
    private List<Teacher> teachers;
    /**
     * *****************************************************************************
     */
//    @JsonIgnore
//    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
//    private List<Teacher> teachers;

}
