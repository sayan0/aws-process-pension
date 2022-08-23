package com.sayan.microservice.processpension.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.sayan.microservice.processpension.ProcessPensionApplication;

@SpringBootTest(classes = ProcessPensionApplication.class)
public class ResourceNotFoundExceptionTest {

	@Test
	void testResourceNotFoundException() {
		Object ex1 = new ResourceNotFoundException("Message");
		Object ex2 = new ResourceNotFoundException();
		assertNotNull(ex1);
		assertNotNull(ex2);
	}
}
