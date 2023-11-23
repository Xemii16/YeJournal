package com.balamut.yejournal.school.request;

public record UserRequest(
        String email,
        String username,
        String password
) {}
