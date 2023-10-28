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
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID schoolId;
    private Long begin;
    @Column(name = "semesterEnd")
    private Long end;
}
