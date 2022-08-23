package com.sayan.microservice.processpension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ProcessPensionApplicationTests {

	@Autowired
	private ProcessPensionApplication application;
	
	@Test
	void contextLoads() throws Exception{
		ProcessPensionApplication.main(new String[] {});
		assertNotNull(application);
	}

}
