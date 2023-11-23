package com.balamut.yejournal.school.response;

import com.balamut.yejournal.core.response.Response;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SchoolResponse extends Response {

    private final String accessToken;

    public SchoolResponse(String accessToken, String response, HttpStatus httpStatus) {
        super(response, httpStatus);
        this.accessToken = accessToken;
    }
}
