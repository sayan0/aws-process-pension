package com.sayan.microservice.processpension.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.sayan.microservice.processpension.ProcessPensionApplication;
import com.sayan.microservice.processpension.bean.PensionDetails;
import com.sayan.microservice.processpension.model.PensionerDetail;
import com.sayan.microservice.processpension.repository.ProcessPensionRepository;

@SpringBootTest(classes = ProcessPensionApplication.class)
public class ProcessPensionServiceTest {

	@Autowired
	private ProcessPensionService service;
	
	@MockBean
	private ProcessPensionRepository repository;
	
	 @Test
	    void test_getPensionDetail_for_bank_private_pension_self() {
	       PensionerDetail pensionerDetail = new PensionerDetail("123", 100, "a;b;private", null, "Sayan","ss", 10000, "self" );
	       PensionDetails pensionDetail = this.service.getPensionDetail(pensionerDetail);
	       assertNotNull(pensionerDetail);
	       assertNotNull(pensionDetail);
	       assertEquals(550, pensionDetail.getBankServiceCharge());
	       assertEquals(8100, pensionDetail.getPensionAmount());
	 	}


	   @Test
	    void test_getPensionDetail_for_bank_private_pension_family() {
	        
		   PensionerDetail pensionerDetail = new PensionerDetail(null, 100, "a;b;private", null, null,null, 10000, "family" );
	       PensionDetails pensionDetail = this.service.getPensionDetail(pensionerDetail);

	       assertNotNull(pensionDetail);
	        assertEquals(550, pensionDetail.getBankServiceCharge());
	        assertEquals(5100, pensionDetail.getPensionAmount());

	   }

	   @Test
	    void test_getPensionDetail_for__pension_invalid() {
	        PensionerDetail pensionerDetail = new PensionerDetail(null, 100, "a;b;private", null, null, null, 10000, "xyz");
	       assertThrows(RuntimeException.class, () -> this.service.getPensionDetail(pensionerDetail));

	   }

	   @Test
	    void test_getPensionDetail_for_bank_public_pension_self() {
	        
		   PensionerDetail pensionerDetail = new PensionerDetail(null, 100, "a;b;public", null, null,null, 10000, "self" );
	       PensionDetails pensionDetail = this.service.getPensionDetail(pensionerDetail);

	       assertNotNull(pensionDetail);
	        assertEquals(500.00, pensionDetail.getBankServiceCharge());
	        assertEquals(8100.00, pensionDetail.getPensionAmount());
	   }


	   @Test
	    void test_getPensionDetail_for_bank_public_pension_family() {
		   PensionerDetail pensionerDetail = new PensionerDetail(null, 100, "a;b;public", null, null,null, 10000, "family" );
	       PensionDetails pensionDetail = this.service.getPensionDetail(pensionerDetail);

	       assertNotNull(pensionDetail);
	        assertEquals(500.00, pensionDetail.getBankServiceCharge());
	        assertEquals(5100.00, pensionDetail.getPensionAmount());
	   }

	   @Test
	    void test_getPensionDetail_for_bank_invalid_pension_valid() {
	       PensionerDetail pensionerDetail = new PensionerDetail(null, 100, "a;b;a", null, null,null, 0, "family" );
	       assertThrows(RuntimeException.class, () -> this.service.getPensionDetail(pensionerDetail));

	   }

	   @Test
	    void test_savePensionDetail() {
	        PensionDetails pensionDetail1 = new PensionDetails("123", 400, 50);
	        PensionDetails pensionDetail2 = pensionDetail1;
	        pensionDetail2.setAadharno("100");
	       when(this.repository.save(pensionDetail1)).thenReturn(pensionDetail2);
	       PensionDetails savedPensionDetail = this.service.savePensionDetail(pensionDetail1);
	        assertNotNull(savedPensionDetail);
	        assertEquals(pensionDetail2, savedPensionDetail);
	   }
	   
}