package com.balamut.yejournal.school;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class SchoolAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    private String region;
    private String area;
    private String street;
    private String zipCode;
}
