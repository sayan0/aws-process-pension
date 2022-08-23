package com.sayan.microservice.processpension.exception;


import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorizedRequestException extends RuntimeException {

    private static final long serialVersionUID = 7231962124231026136L;

    public UnAuthorizedRequestException() {
        super();
    }

    public UnAuthorizedRequestException(String message) {
        super(message);
    }

}