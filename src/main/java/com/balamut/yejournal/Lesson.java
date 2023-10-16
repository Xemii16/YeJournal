package com.balamut.yejournal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Lesson {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String cabinet;

    private UUID teacher;

    private UUID school;

    private UUID schoolClass;

}
