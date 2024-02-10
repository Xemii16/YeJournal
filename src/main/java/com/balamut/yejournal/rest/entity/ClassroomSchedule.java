package com.balamut.yejournal.rest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "classroom_schedules")
public class ClassroomSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    private Classroom classroom;
    @OneToOne
    @JoinColumn(name = "call_schedule_id")
    private CallSchedule timeFrame;
    @ManyToOne
    private Lesson lesson;

    @ManyToOne
    private Cabinet cabinet;
}
