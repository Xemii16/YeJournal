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
@Table(name = "schools")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "short_name", nullable = false)
    private String shortName;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "number_telephone", nullable = false)
    private String numberTelephone;
    @OneToOne
    @JoinColumn(name = "director_id")
    private Director director;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "url_site")
    private String urlSite;
    @OneToMany
    private List<Semester> semesters;
    @OneToMany
    private List<Teacher> teachers;
}
