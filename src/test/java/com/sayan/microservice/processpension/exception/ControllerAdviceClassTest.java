package com.sayan.microservice.processpension.exception;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.sayan.microservice.processpension.ProcessPensionApplication;

@SpringBootTest(classes = ProcessPensionApplication.class)
public class ControllerAdviceClassTest {
	@Autowired
    private ControllerAdviceClass controllerAdviceClass;

    @Test
    void test_bean() throws Exception {
        assertNotNull(this.controllerAdviceClass);        
    }

    @Test
    void test_nullPointerException() throws Exception {
        ResponseEntity<ExceptionResponse> handleAnyNullPointerException = this.controllerAdviceClass
                .handleAnyNullPointerException(new NullPointerException(), null);
        assertNotNull(handleAnyNullPointerException);
    }
   
    
    @Test
    void test_ResourceNotFoundException() throws Exception {
        ResponseEntity<ExceptionResponse> handleResourceNotFoundException = this.controllerAdviceClass
                .handleResourceNotFoundException(new NullPointerException(), null);
        assertNotNull(handleResourceNotFoundException);
    }
    
    @Test
    void test_UnAuthReqException() throws Exception {
        ResponseEntity<ExceptionResponse> handleUnAuthReqException = this.controllerAdviceClass
                .handleUnAuthReqException(new NullPointerException(), null);
        assertNotNull(handleUnAuthReqException);
    }
    
}
