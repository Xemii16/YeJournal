package com.balamut.yejournal.school.service;

import com.balamut.yejournal.school.request.AdminRequest;
import com.balamut.yejournal.school.request.SchoolRequest;
import com.balamut.yejournal.school.request.UserRequest;
import com.balamut.yejournal.school.response.SchoolResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT)
class SchoolServiceTest {

    private static final String ENDPOINT = "http://localhost:8080/api/v1/school";
    private static final String CONTENT_TYPE = "application/json";
    @Autowired
    private PasswordEncoder passwordEncoder;
    private RestTemplate restTemplate;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @BeforeEach
    public void setup() {
        this.restTemplate = new RestTemplate();
    }

    @Test
    void registerSchool() {
        SchoolRequest request = new SchoolRequest(
                "Test",
                new AdminRequest(
                        new UserRequest(
                                "test@test.com",
                                "test",
                                "test"
                        )
                )
        );
        ResponseEntity<SchoolResponse> response = restTemplate.postForEntity(ENDPOINT, request, SchoolResponse.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
    }
}