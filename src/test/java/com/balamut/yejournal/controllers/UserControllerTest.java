package com.balamut.yejournal.controllers;

import com.balamut.yejournal.entities.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@Transactional
@AutoConfigureDataJpa
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private User user;
    private Gson gson;

    @BeforeEach
    void setUp() {
        this.user = new User(
                UUID.randomUUID(),
                "test@gmail.com",
                "xemii16",
                "12345678",
                "Maks",
                "Veray",
                "Goggl",
                Instant.now().toEpochMilli()
        );
        this.gson = new GsonBuilder()
                .setPrettyPrinting().create();
    }

    @Test
    void createUserSuccessful() throws Exception {
        user.setId(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/user").contentType("application/json").content(gson.toJson(user)))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }
}