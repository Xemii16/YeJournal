package com.balamut.yejournal.controller;

import com.balamut.yejournal.entity.Teacher;
import com.balamut.yejournal.repository.TeacherRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DataJpaTest
class TeacherControllerTest {

    private MockMvc mockMvc;
    private Gson gson;
    private Teacher teacher;
    private String contentType = "application/json";
    private String uuid = "4d16f742-18d8-4c94-9c7e-34c5d9b58d72";

    @Autowired
    private TeacherRepository teacherRepository;

    @BeforeEach
    void setUp() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting().create();
        TeacherController controller = new TeacherController(teacherRepository);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
        // TODO Replace random UUID to real
        this.teacher = new Teacher(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                "Maks",
                "Bebra",
                "Verbat",
                Instant.now().toEpochMilli(),
                "teacher"
        );
    }

    @Test
    void createTeacherSuccessful() throws Exception {
        mockMvc.perform(post("/teacher").contentType("application/json").content(gson.toJson(teacher)))
                .andDo(print()).andExpect(status().is2xxSuccessful()).andExpect(content().contentType(contentType));
    }

    @Test
    void createTeacherConflict() throws Exception {
        Teacher saved = teacherRepository.save(teacher);
        teacher.setId(saved.getId());
        mockMvc.perform(post("/teacher").contentType("application/json").content(gson.toJson(teacher)))
                .andDo(print()).andExpect(status().isConflict());
    }

    @Test
    void getTeacherNotFound() throws Exception {
        mockMvc.perform(get("/teacher/" + uuid)).andExpect(status().isNotFound());
    }

    @Test
    void getTeacherBadRequest() throws Exception {
        String uuid = "bad";
        mockMvc.perform(get("/teacher/" + uuid)).andExpect(status().isBadRequest());
    }

    @Test
    void deleteTeacherSuccessful() throws Exception {
        Teacher savedTeacher = teacherRepository.save(teacher);
        mockMvc.perform(delete("/teacher/" + savedTeacher.getId().toString())).andExpect(status().is2xxSuccessful());
        assertThat(teacherRepository.findById(savedTeacher.getId())).isEmpty();
    }

    @Test
    void deleteTeacherNotFound() throws Exception {
        mockMvc.perform(delete("/teacher/" + uuid)).andExpect(status().isNotFound());
    }

    @Test
    void updateTeacherSuccessful() throws Exception {
        Teacher saved = teacherRepository.save(teacher);
        String builder = '"' +
                teacher.getId().toString() +
                '"';
        mockMvc.perform(put("/teacher")
                        .contentType(contentType)
                        .content(gson.toJson(saved)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(builder));
    }

    @Test
    void updateTeacherNotFound() throws Exception {
        mockMvc.perform(put("/teacher")
                        .contentType(contentType)
                        .content(gson.toJson(teacher)))
                .andExpect(status().isNotFound());
    }

}