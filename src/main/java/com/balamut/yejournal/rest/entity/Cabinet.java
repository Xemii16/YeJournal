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
@Table(name = "cabinets")
public class Cabinet {



    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "cabinet_number_name", nullable = false, unique = true)
    private String numberName;
    @Column(name = "cabinet_name")
    private String name;
}
