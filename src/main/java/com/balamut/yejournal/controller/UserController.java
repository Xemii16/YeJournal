package com.balamut.yejournal.controller;

import com.balamut.yejournal.authentication.entity.User;
import com.balamut.yejournal.authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping("")
    public UUID createUser(@RequestBody @NotNull User user) throws ResponseStatusException {
        if (repository.existsById(user.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Use PUT method for update user");
        }
        User savedUser = repository.save(user);
        return savedUser.getId();
    }

    @GetMapping("{uuid}")
    public User getUser(@PathVariable UUID uuid) throws ResponseStatusException {
        Optional<User> optional = repository.findById(uuid);
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return optional.get();
    }

    @DeleteMapping("{uuid}")
    public void deleteUser(@PathVariable UUID uuid) throws ResponseStatusException {
        if (!repository.existsById(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(uuid);
    }

    @PutMapping("")
    public UUID updateUser(@RequestBody @NotNull User user) throws ResponseStatusException {
        if (!repository.existsById(user.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        User saved = repository.save(user);
        return saved.getId();
    }
}
