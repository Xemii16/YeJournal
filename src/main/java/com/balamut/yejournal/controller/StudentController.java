package com.balamut.yejournal.controller;

import com.balamut.yejournal.entity.Student;
import com.balamut.yejournal.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @PostMapping("")
    public UUID createStudent(@RequestBody @NotNull Student student) throws ResponseStatusException {
        if (repository.existsById(student.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Use PUT method for update user");
        }
        Student savedStudent = repository.save(student);
        return savedStudent.getId();
    }

    @GetMapping("{uuid}")
    public Student getStudent(@PathVariable UUID uuid) throws ResponseStatusException {
        Optional<Student> optional = repository.findById(uuid);
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return optional.get();
    }

    @DeleteMapping("{uuid}")
    public void deleteStudent(@PathVariable UUID uuid) throws ResponseStatusException {
        if (!repository.existsById(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(uuid);
    }

    @PutMapping("")
    public UUID updateStudent(@RequestBody @NotNull Student student) throws ResponseStatusException {
        if (!repository.existsById(student.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Student saved = repository.save(student);
        return saved.getId();
    }

}
