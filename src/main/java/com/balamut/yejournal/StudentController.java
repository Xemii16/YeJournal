package com.balamut.yejournal;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private StudentRepository repository;

    @GetMapping("{id}")
    public Student getStudent(@PathVariable UUID id, HttpServletResponse response) throws IOException {
        boolean studentExists;
        Student student;
        studentExists = repository.existsByUuid(id);
        if (!studentExists) response.sendError(HttpServletResponse.SC_NOT_FOUND, "There is no such student");
        student = repository.getByUuid(id);
        return student;
    }
}
