package com.balamut.yejournal.school;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String fullName;
    @OneToOne
    @JoinColumn(name = "address_id")
    private SchoolAddress address;

    private String telephones;
    private String email;
    private String site;
}
