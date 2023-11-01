package com.balamut.yejournal.controller;

import com.balamut.yejournal.entity.Admin;
import com.balamut.yejournal.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private AdminRepository repository;

    public AdminController(AdminRepository repository) {
        this.repository = repository;
    }

    @PostMapping("")
    public UUID createAdmin(@RequestBody @NotNull Admin admin) throws ResponseStatusException {
        if (repository.existsById(admin.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Use PUT method for update user");
        }
        Admin savedAdmin = repository.save(admin);
        return savedAdmin.getId();
    }

    @GetMapping("{uuid}")
    public Admin getAdmin(@PathVariable UUID uuid) throws ResponseStatusException {
        Optional<Admin> optional = repository.findById(uuid);
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return optional.get();
    }

    @DeleteMapping("{uuid}")
    public void deleteAdmin(@PathVariable UUID uuid) throws ResponseStatusException {
        if (!repository.existsById(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(uuid);
    }

    @PutMapping("")
    public UUID updateAdmin(@RequestBody @NotNull Admin admin) throws ResponseStatusException {
        if (!repository.existsById(admin.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Admin saved = repository.save(admin);
        return saved.getId();
    }
}
