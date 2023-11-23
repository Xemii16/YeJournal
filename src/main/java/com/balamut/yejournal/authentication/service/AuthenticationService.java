package com.balamut.yejournal.authentication.service;

import com.balamut.yejournal.authentication.entity.Role;
import com.balamut.yejournal.authentication.entity.User;
import com.balamut.yejournal.authentication.repository.UserRepository;
import com.balamut.yejournal.authentication.request.AuthenticationRequest;
import com.balamut.yejournal.authentication.request.RegisterRequest;
import com.balamut.yejournal.authentication.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(@NotNull RegisterRequest request) {
        User user = new User(
                null,
                request.email(),
                request.username(),
                passwordEncoder.encode(request.password()),
                List.of(Role.USER)
        );
        repository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(
                jwtToken,
                null,
                HttpStatus.OK
        );
    }

    public AuthenticationResponse authenticate(@NotNull AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        User user = repository.findByEmail(request.email()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(
                jwtToken,
                null,
                HttpStatus.OK
        );
    }

}
