package com.balamut.yejournal.rest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "classrooms")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne
    @JoinColumn(name = "class_head_id")
    private ClassHead classHead;
    @Column(nullable = false, length = 2)
    private Integer classNumber;
    @Column(nullable = false, length = 1)
    private String classLetter;
    @OneToMany
    @JoinColumn(name = "classmate_id")
    private List<Classmate> classmates;
    @OneToMany(mappedBy = "classroom")
    private List<ClassroomSchedule> schedules;
}
