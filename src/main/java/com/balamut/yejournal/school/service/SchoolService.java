package com.balamut.yejournal.school.service;

import com.balamut.yejournal.authentication.entity.Role;
import com.balamut.yejournal.authentication.entity.User;
import com.balamut.yejournal.authentication.repository.UserRepository;
import com.balamut.yejournal.authentication.request.AuthenticationRequest;
import com.balamut.yejournal.authentication.response.AuthenticationResponse;
import com.balamut.yejournal.authentication.service.AuthenticationService;
import com.balamut.yejournal.school.Admin;
import com.balamut.yejournal.school.School;
import com.balamut.yejournal.school.repository.AdminRepository;
import com.balamut.yejournal.school.repository.SchoolRepository;
import com.balamut.yejournal.school.request.AdminRequest;
import com.balamut.yejournal.school.request.SchoolRequest;
import com.balamut.yejournal.school.request.UserRequest;
import com.balamut.yejournal.school.response.SchoolResponse;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;


    public SchoolResponse registerSchool(SchoolRequest schoolRequest) {
        School school = getSchool(schoolRequest);
        Admin admin = getAdmin(schoolRequest.admin());
        User user = getUser(schoolRequest.admin().user());
        User savedUser;
        Admin savedAdmin;
        AuthenticationResponse authenticationResponse;

        if (userRepository.findByEmail(user.getEmail()).isPresent()){
            return new SchoolResponse(
                    null,
                    "The email address is already in use by another account",
                    HttpStatus.BAD_REQUEST
            );
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        savedUser = userRepository.save(user);
        admin.setUser(savedUser);
        savedAdmin = adminRepository.save(admin);
        school.setAdmins(Collections.singletonList(savedAdmin));
        schoolRepository.save(school);
        authenticationResponse = authenticationService.authenticate(
                new AuthenticationRequest(
                        savedUser.getEmail(),
                        savedUser.getPassword()
                )
        );

        return new SchoolResponse(
                authenticationResponse.getAccessToken(),
                null,
                HttpStatus.OK
        );
    }

    private User getUser(@NotNull UserRequest request) {
        return User.builder()
                .username(request.username())
                .email(request.email())
                .password(request.password())
                .roles(List.of(Role.USER))
                .build();
    }

    private Admin getAdmin(@NotNull AdminRequest request) {
        return Admin.builder()
                .user(getUser(request.user()))
                .build();
    }

    private School getSchool(@NotNull SchoolRequest request) {
        return School.builder()
                .admins(List.of(getAdmin(request.admin())))
                .name(request.name())
                .teachers(List.of())
                .build();
    }
}
