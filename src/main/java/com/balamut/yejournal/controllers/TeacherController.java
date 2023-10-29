package com.balamut.yejournal.controllers;

import com.balamut.yejournal.entities.Admin;
import com.balamut.yejournal.entities.Teacher;
import com.balamut.yejournal.repositories.TeacherRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private TeacherRepository repository;

    public TeacherController(TeacherRepository repository) {
        this.repository = repository;
    }

    @PostMapping("")
    public UUID createTeacher(@RequestBody @NotNull Teacher admin) throws ResponseStatusException {
        if (repository.existsById(admin.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Use PUT method for update user");
        }
        Teacher savedTeacher = repository.save(admin);
        return savedTeacher.getId();
    }

    @GetMapping("{uuid}")
    public Teacher getTeacher(@PathVariable UUID uuid) throws ResponseStatusException {
        Optional<Teacher> optional = repository.findById(uuid);
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return optional.get();
    }

    @DeleteMapping("{uuid}")
    public void deleteTeacher(@PathVariable UUID uuid) throws ResponseStatusException {
        if (!repository.existsById(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(uuid);
    }

    @PutMapping("")
    public UUID updateTeacher(@RequestBody @NotNull Teacher admin) throws ResponseStatusException {
        if (!repository.existsById(admin.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Teacher saved = repository.save(admin);
        return saved.getId();
    }
}
