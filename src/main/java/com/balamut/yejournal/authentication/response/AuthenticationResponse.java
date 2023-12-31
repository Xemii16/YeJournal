package com.balamut.yejournal.authentication.response;

import com.balamut.yejournal.core.response.Response;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AuthenticationResponse extends Response {

    private final String accessToken;

    public AuthenticationResponse(String accessToken, String response, HttpStatus httpStatus) {
        super(response, httpStatus);
        this.accessToken = accessToken;
    }
}
