package com.sayan.microservice.processpension.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@RestController
@ControllerAdvice
public class ControllerAdviceClass extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ControllerAdviceClass.class);

    @ExceptionHandler(NullPointerException.class)
    public final ResponseEntity<ExceptionResponse> handleAnyNullPointerException(
            Exception ex,
            WebRequest request) throws Exception {
        log.error("handleGenericCustomException()");
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionResponse exceptionResponse = new ExceptionResponse(status, "NULL_OBJECT_PASSED");
        return new ResponseEntity<>(exceptionResponse, status);
    }
   

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleResourceNotFoundException(
            Exception ex,
            WebRequest request) throws Exception {
        log.error("handleUserNotFoundException()");
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionResponse exceptionResponse = new ExceptionResponse(status, "PENSIONER_RESOURCE_NOT_FOUND");
        return new ResponseEntity<>(exceptionResponse, status);
    }

    @ExceptionHandler(UnAuthorizedRequestException.class)
    public final ResponseEntity<ExceptionResponse> handleUnAuthReqException(
            Exception ex,
            WebRequest request) throws Exception {
        log.error("handleUserNotFoundException()");
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ExceptionResponse exceptionResponse = new ExceptionResponse(status, "AUTHORIZATION_FAILED");
        return new ResponseEntity<>(exceptionResponse, status);
    }

   
}