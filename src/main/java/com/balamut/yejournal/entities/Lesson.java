package com.balamut.yejournal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uniqueId")
    private UUID id;
    private UUID classroomId;
    private UUID teacherId;
    private String name;
    private Long begin;
    @Column(name = "lessonEnd")
    private Long end;
}
