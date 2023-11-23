package com.balamut.yejournal.core.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class Response {
    private final String response;
    private final HttpStatus httpStatus;

    public Response(String response, HttpStatus httpStatus) {
        this.response = response;
        this.httpStatus = httpStatus;
    }

}
