package com.balamut.yejournal.controllers;

import com.balamut.yejournal.entities.Student;
import com.balamut.yejournal.repositories.StudentRepository;
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
class StudentControllerTest {

    private MockMvc mockMvc;
    private Gson gson;
    private Student student;
    private String contentType = "application/json";
    private String uuid = "4d16f742-18d8-4c94-9c7e-34c5d9b58d72";

    @Autowired
    private StudentRepository teacherRepository;

    @BeforeEach
    void setUp() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting().create();
        StudentController controller = new StudentController(teacherRepository);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
        // TODO Replace random UUID to real
        this.student = new Student(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                "Maks",
                "Bebra",
                "Verbat",
                Instant.now().toEpochMilli()
        );
    }

    @Test
    void createStudentSuccessful() throws Exception {
        mockMvc.perform(post("/student").contentType("application/json").content(gson.toJson(student)))
                .andDo(print()).andExpect(status().is2xxSuccessful()).andExpect(content().contentType(contentType));
    }

    @Test
    void createStudentConflict() throws Exception {
        Student saved = teacherRepository.save(student);
        student.setId(saved.getId());
        mockMvc.perform(post("/student").contentType("application/json").content(gson.toJson(student)))
                .andDo(print()).andExpect(status().isConflict());
    }

    @Test
    void getStudentNotFound() throws Exception {
        mockMvc.perform(get("/student/" + uuid)).andExpect(status().isNotFound());
    }

    @Test
    void getStudentBadRequest() throws Exception {
        String uuid = "bad";
        mockMvc.perform(get("/student/" + uuid)).andExpect(status().isBadRequest());
    }

    @Test
    void deleteStudentSuccessful() throws Exception {
        Student savedStudent = teacherRepository.save(student);
        mockMvc.perform(delete("/student/" + savedStudent.getId().toString())).andExpect(status().is2xxSuccessful());
        assertThat(teacherRepository.findById(savedStudent.getId())).isEmpty();
    }

    @Test
    void deleteStudentNotFound() throws Exception {
        mockMvc.perform(delete("/student/" + uuid)).andExpect(status().isNotFound());
    }

    @Test
    void updateStudentSuccessful() throws Exception {
        Student saved = teacherRepository.save(student);
        String builder = '"' +
                student.getId().toString() +
                '"';
        mockMvc.perform(put("/student")
                        .contentType(contentType)
                        .content(gson.toJson(saved)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(builder));
    }

    @Test
    void updateStudentNotFound() throws Exception {
        mockMvc.perform(put("/student")
                        .contentType(contentType)
                        .content(gson.toJson(student)))
                .andExpect(status().isNotFound());
    }


}