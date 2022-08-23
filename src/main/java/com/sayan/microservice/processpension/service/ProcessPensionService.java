package com.sayan.microservice.processpension.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sayan.microservice.processpension.bean.PensionDetails;
import com.sayan.microservice.processpension.model.PensionerDetail;
import com.sayan.microservice.processpension.repository.ProcessPensionRepository;

@Service
public class ProcessPensionService {
	
	 private static final Logger log = LoggerFactory.getLogger(ProcessPensionService.class);

	    @Autowired
	    private ProcessPensionRepository pensionDetailRepository;

	    public PensionDetails getPensionDetail(PensionerDetail pensionerDetails) {
	        log.info("getPensionDetail Started");
	        float percentage = 0;
	        int bankServiceCharge = 0;
	        
	        if (pensionerDetails.getType().equalsIgnoreCase("self")) {
	            percentage = (float) 0.8;
	        } else if (pensionerDetails.getType().equalsIgnoreCase("family")) {
	            percentage = (float) 0.5;
	        } else {
	            throw new RuntimeException("Invalid value passed");
	        }
	        float pensionAmount =  (pensionerDetails.getSalary() * percentage) + pensionerDetails.getAllowances();
	        
	        if (pensionerDetails.getBankType().equalsIgnoreCase("private")) {
	            bankServiceCharge = 550;
	        } else if (pensionerDetails.getBankType().equalsIgnoreCase("public")) {
	            bankServiceCharge = 500;
	        } else {
	            throw new RuntimeException("Invalid value passed");
	        }

	        PensionDetails pensionDetail = new PensionDetails(
	                pensionerDetails.getAadharno(),
	                pensionAmount,
	                bankServiceCharge);

	        log.info("getPensionDetail Ended");
	        return pensionDetail;

	    }

	    public PensionDetails savePensionDetail(PensionDetails pensionDetail) {
	        log.info("savePensionDetail Started");
	        log.info("savePensionDetail Ended");
	        return pensionDetailRepository.save(pensionDetail);

	    }
	}