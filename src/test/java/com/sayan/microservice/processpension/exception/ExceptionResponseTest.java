package com.sayan.microservice.processpension.exception;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.sayan.microservice.processpension.ProcessPensionApplication;

@SpringBootTest(classes = ProcessPensionApplication.class)
public class ExceptionResponseTest {
	
	@Test
	void testResourceNotFoundException() {
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ExceptionResponse.class);
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.OK, null);
		assertNotNull(exceptionResponse);
	}
}
