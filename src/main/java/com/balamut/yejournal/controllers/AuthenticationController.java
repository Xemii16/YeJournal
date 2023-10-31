package com.balamut.yejournal.controllers;

import com.balamut.yejournal.entities.User;
import com.balamut.yejournal.repositories.RoleRepository;
import com.balamut.yejournal.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public UUID registerUser(@RequestBody @NotNull RegisterForm form) throws ResponseStatusException {
        if (userRepository.existsByUsername(form.username())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is taken");
        }
        User user = new User(
            null,
                form.email(),
                form.username(),
                passwordEncoder.encode(form.password()),
                new ArrayList<>()
        );
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    public record RegisterForm(String username, String email, String password) {
    }
}
