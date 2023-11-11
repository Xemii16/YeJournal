package com.balamut.yejournal.school;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SchoolAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    private String region;
    private String area;
    private String street;
    private String zipCode;
}
