package com.balamut.yejournal.authentication.entity;

import lombok.Getter;

@Getter
public enum Role {
    USER("USER");
    private final String name;

    Role(String name) {
        this.name = name;
    }

}
