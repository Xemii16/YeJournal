package com.balamut.yejournal.school;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String fullName;
    @OneToOne
    @JoinColumn(name = "address_id")
    private SchoolAddress address;

    @OneToMany(mappedBy = "school")
    private List<Admin> admins;

    @OneToMany(mappedBy = "school")
    private List<Teacher> teachers;
    private String telephones;
    private String email;
    private String site;
}
