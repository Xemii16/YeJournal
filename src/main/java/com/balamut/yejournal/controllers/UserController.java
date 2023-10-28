package com.balamut.yejournal.controllers;

import com.balamut.yejournal.entities.User;
import com.balamut.yejournal.repositories.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Data
@RequestMapping("/user")
public class UserController {

    private UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping("")
    public UUID createUser(@RequestBody User user) throws ResponseStatusException {
        if (user.getFirstName() == null){
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "First name can not be blank");
        }
        if (user.getLastName() == null){
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Last name can not be blank");
        }
        if (user.getPassword() == null){
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Password can not be blank");
        }
        if (user.getUsername() == null){
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Username can not be blank");
        }
        if (user.getEmail() == null & repository.existsByEmail(user.getEmail())){
            throw new ResponseStatusException(HttpStatusCode.valueOf(409), "This email is not available");
        }
        if (repository.existsByUsername(user.getUsername())){
            throw new ResponseStatusException(HttpStatusCode.valueOf(409), "This username is not available");
        }
        UUID uuid = UUID.randomUUID();
        user.setId(uuid);
        repository.save(user);
        return uuid;
    }
}
