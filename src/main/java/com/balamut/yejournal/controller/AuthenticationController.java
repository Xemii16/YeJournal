package com.balamut.yejournal.controller;

import com.balamut.yejournal.entity.User;
import com.balamut.yejournal.repository.RoleRepository;
import com.balamut.yejournal.repository.UserRepository;
import com.balamut.yejournal.response.AuthenticateResponse;
import com.balamut.yejournal.security.JWTGenerator;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private JWTGenerator jwtGenerator;

    @PostMapping("/register")
    public User registerUser(@RequestBody @NotNull RegisterForm form) throws ResponseStatusException {
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
        return userRepository.save(user);
    }

    @PostMapping("/authenticate")
    public AuthenticateResponse login(@RequestBody LoginForm form) throws ResponseStatusException{
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(form.username(), form.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtGenerator.generateToken(authentication);
        return new AuthenticateResponse(accessToken, "Bearer ");
    }

    public record RegisterForm(String username, String email, String password) {}

    public record LoginForm(String username, String password) {}
}
