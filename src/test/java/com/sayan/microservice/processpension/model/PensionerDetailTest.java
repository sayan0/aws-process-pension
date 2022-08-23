package com.sayan.microservice.processpension.model;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;

import com.sayan.microservice.processpension.ProcessPensionApplication;

@SpringBootTest(classes = ProcessPensionApplication.class)
public class PensionerDetailTest {
	
	@Test
	void testBean() throws Exception {
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(PensionerDetail.class);
		//PensionDetails bean = new PensionDetails(null,null,null);
//		assertNotNull(bean);
	}
}