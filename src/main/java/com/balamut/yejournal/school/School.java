package com.balamut.yejournal.school;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @OneToMany(mappedBy = "school")
    private List<Admin> admins;

    @OneToMany(mappedBy = "school")
    private List<Teacher> teachers;
}
