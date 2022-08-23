package com.sayan.microservice.processpension.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ExceptionResponse {

    private Date timestamp;
    private int code;
    private String status;
    private String message;

    public ExceptionResponse() {
        timestamp = new Date();
    }

    public ExceptionResponse(HttpStatus httpStatus, String message) {
        this();

        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.message = message;
    }

}
