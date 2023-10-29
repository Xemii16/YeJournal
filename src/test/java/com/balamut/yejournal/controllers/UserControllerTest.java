package com.balamut.yejournal.controllers;

import com.balamut.yejournal.entities.User;
import com.balamut.yejournal.repositories.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserControllerTest {

    private MockMvc mockMvc;
    private Gson gson;
    private User user;
    private String contentType = "application/json";
    private String uuid = "4d16f742-18d8-4c94-9c7e-34c5d9b58d72";

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting().create();
        UserController controller = new UserController(userRepository);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
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
    }

    @Test
    void createUserSuccessful() throws Exception {
        mockMvc.perform(post("/user").contentType("application/json").content(gson.toJson(user)))
                .andDo(print()).andExpect(status().is2xxSuccessful()).andExpect(content().contentType(contentType));
    }

    @Test
    void createUserConflict() throws Exception {
        User saved = userRepository.save(user);
        user.setId(saved.getId());
        mockMvc.perform(post("/user").contentType("application/json").content(gson.toJson(user)))
                .andDo(print()).andExpect(status().isConflict());
    }

    @Test
    void getUserNotFound() throws Exception {
        mockMvc.perform(get("/user/" + uuid)).andExpect(status().isNotFound());
    }

    @Test
    void getUserBadRequest() throws Exception {
        String uuid = "bad";
        mockMvc.perform(get("/user/" + uuid)).andExpect(status().isBadRequest());
    }

    @Test
    void deleteUserSuccessful() throws Exception {
        User savedUser = userRepository.save(user);
        mockMvc.perform(delete("/user/" + savedUser.getId().toString())).andExpect(status().is2xxSuccessful());
        assertThat(userRepository.findById(savedUser.getId())).isEmpty();
    }

    @Test
    void deleteUserNotFound() throws Exception {
        mockMvc.perform(delete("/user/" + uuid)).andExpect(status().isNotFound());
    }

    @Test
    void updateUserSuccessful() throws Exception {
        User saved = userRepository.save(user);
        StringBuilder builder = new StringBuilder();
        builder.append('"');
        builder.append(user.getId().toString());
        builder.append('"');
        mockMvc.perform(put("/user")
                .contentType(contentType)
                .content(gson.toJson(saved)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(builder.toString()));
    }

    @Test
    void updateUserNotFound() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(contentType)
                .content(gson.toJson(user)))
                .andExpect(status().isNotFound());
    }
}