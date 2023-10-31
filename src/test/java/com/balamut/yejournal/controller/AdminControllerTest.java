package com.balamut.yejournal.controller;

import com.balamut.yejournal.entity.Admin;
import com.balamut.yejournal.repository.AdminRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DataJpaTest
class AdminControllerTest {

    private MockMvc mockMvc;
    private Gson gson;
    private Admin admin;
    private String contentType = "application/json";
    private String uuid = "4d16f742-18d8-4c94-9c7e-34c5d9b58d72";

    @Autowired
    private AdminRepository adminRepository;

    @BeforeEach
    void setUp() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting().create();
        AdminController controller = new AdminController(adminRepository);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
        // TODO Replace random UUID to real
        this.admin = new Admin(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                "Директор"
        );
    }

    @Test
    void createAdminSuccessful() throws Exception {
        mockMvc.perform(post("/admin").contentType("application/json").content(gson.toJson(admin)))
                .andDo(print()).andExpect(status().is2xxSuccessful()).andExpect(content().contentType(contentType));
    }

    @Test
    void createAdminConflict() throws Exception {
        Admin saved = adminRepository.save(admin);
        admin.setId(saved.getId());
        mockMvc.perform(post("/admin").contentType("application/json").content(gson.toJson(admin)))
                .andDo(print()).andExpect(status().isConflict());
    }

    @Test
    void getAdminNotFound() throws Exception {
        mockMvc.perform(get("/admin/" + uuid)).andExpect(status().isNotFound());
    }

    @Test
    void getAdminBadRequest() throws Exception {
        String uuid = "bad";
        mockMvc.perform(get("/admin/" + uuid)).andExpect(status().isBadRequest());
    }

    @Test
    void deleteAdminSuccessful() throws Exception {
        Admin savedAdmin = adminRepository.save(admin);
        mockMvc.perform(delete("/admin/" + savedAdmin.getId().toString())).andExpect(status().is2xxSuccessful());
        assertThat(adminRepository.findById(savedAdmin.getId())).isEmpty();
    }

    @Test
    void deleteAdminNotFound() throws Exception {
        mockMvc.perform(delete("/admin/" + uuid)).andExpect(status().isNotFound());
    }

    @Test
    void updateAdminSuccessful() throws Exception {
        Admin saved = adminRepository.save(admin);
        String builder = '"' +
                admin.getId().toString() +
                '"';
        mockMvc.perform(put("/admin")
                        .contentType(contentType)
                        .content(gson.toJson(saved)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(builder));
    }

    @Test
    void updateAdminNotFound() throws Exception {
        mockMvc.perform(put("/admin")
                        .contentType(contentType)
                        .content(gson.toJson(admin)))
                .andExpect(status().isNotFound());
    }

}