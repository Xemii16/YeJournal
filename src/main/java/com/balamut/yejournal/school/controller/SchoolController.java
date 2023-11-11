package com.balamut.yejournal.school.controller;

import com.balamut.yejournal.school.request.SchoolRequest;
import com.balamut.yejournal.school.service.SchoolService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/school")
public class SchoolController {

    private final SchoolService service;

    public SchoolController(SchoolService service) {
        this.service = service;
    }

    @PostMapping("")
    public UUID createSchool(@RequestBody SchoolRequest schoolRequest) {
        return service.createSchool(schoolRequest);
    }

    @GetMapping
    public String test(){
        return "grgr";
    }
}
