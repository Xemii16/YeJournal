package com.balamut.yejournal.controller;

import com.balamut.yejournal.entity.Teacher;
import com.balamut.yejournal.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {

    private TeacherRepository repository;

    public TeacherController(TeacherRepository repository) {
        this.repository = repository;
    }

    @PostMapping("")
    public UUID createTeacher(@RequestBody @NotNull Teacher teacher) throws ResponseStatusException {
        if (repository.existsById(teacher.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Use PUT method for update user");
        }
        Teacher savedTeacher = repository.save(teacher);
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
    public UUID updateTeacher(@RequestBody @NotNull Teacher teacher) throws ResponseStatusException {
        if (!repository.existsById(teacher.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Teacher saved = repository.save(teacher);
        return saved.getId();
    }
}
