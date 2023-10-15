package com.balamut.yejournal;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private StudentRepository repository;

    @GetMapping("student/{id}")
    @SneakyThrows
    public Student getStudent(@PathVariable UUID id, HttpServletResponse response) {
        boolean studentExists;
        Student student;
        studentExists = repository.existsByUuid(id);
        if (!studentExists) response.sendError(HttpServletResponse.SC_NOT_FOUND, "There is no such student");
        student = repository.getByUuid(id);
        return student;
    }
}
