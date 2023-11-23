package com.balamut.yejournal.school.controller;

import com.balamut.yejournal.school.request.SchoolRequest;
import com.balamut.yejournal.school.response.SchoolResponse;
import com.balamut.yejournal.school.service.SchoolService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/school")
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("")
    public SchoolResponse registerSchool(@RequestBody SchoolRequest schoolRequest) {
        return schoolService.registerSchool(schoolRequest);
    }
}
