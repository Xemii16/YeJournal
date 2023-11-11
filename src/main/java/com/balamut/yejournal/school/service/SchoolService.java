package com.balamut.yejournal.school.service;

import com.balamut.yejournal.school.School;
import com.balamut.yejournal.school.SchoolAddress;
import com.balamut.yejournal.school.repository.SchoolRepository;
import com.balamut.yejournal.school.request.SchoolAddressRequest;
import com.balamut.yejournal.school.request.SchoolRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class SchoolService {

    private final SchoolRepository repository;

    public SchoolService(SchoolRepository repository) {
        this.repository = repository;
    }

    public UUID createSchool(SchoolRequest schoolRequest) {
        SchoolAddressRequest schoolAddressRequest = schoolRequest.address();
        SchoolAddress schoolAddress = SchoolAddress.builder()
                .area(schoolAddressRequest.area())
                .street(schoolAddressRequest.street())
                .region(schoolAddressRequest.region())
                .zipCode(schoolAddressRequest.zipCode())
                .build();
        School school = School.builder()
                .name(schoolRequest.name())
                .fullName(schoolRequest.fullName())
                .address(schoolAddress)
                .site(schoolRequest.site())
                .email(schoolRequest.email())
                .telephones(schoolRequest.telephones())
                .teachers(new ArrayList<>())
                .admins(new ArrayList<>())
                .build();
        School savedSchool = repository.save(school);
        return savedSchool.getId();
    }
}
