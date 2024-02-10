package com.balamut.yejournal.rest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "call_schedules")
public class CallSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "schedule_date", nullable = false)
    private Date date;
    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;
    @Column(name = "start_minutes", nullable = false)
    private Integer startMinutes;
    @Column(name = "stop_minutes", nullable = false)
    private Integer stopMinutes;
}
