package com.balamut.yejournal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Instant;
import java.util.UUID;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private UUID school;

    private String username;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String surname;

    private Instant birthday;
}
