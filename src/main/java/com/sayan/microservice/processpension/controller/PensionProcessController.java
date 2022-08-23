package com.sayan.microservice.processpension.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sayan.microservice.processpension.proxy.AuthProxy;
import com.sayan.microservice.processpension.proxy.PensionDetailProxy;
import com.sayan.microservice.processpension.service.ProcessPensionService;
import com.sayan.microservice.processpension.bean.PensionDetails;
import com.sayan.microservice.processpension.exception.ResourceNotFoundException;
import com.sayan.microservice.processpension.exception.UnAuthorizedRequestException;
import com.sayan.microservice.processpension.model.PensionerDetail;
import com.sayan.microservice.processpension.model.ProcessPensionInput;

@RestController
@CrossOrigin(origins = "*")
public class PensionProcessController {

	@Autowired
	private AuthProxy authProxy;

	@Autowired
	private ProcessPensionService processService;

	@Autowired
	private PensionDetailProxy pdetailproxy;

	private static final Logger logger = LoggerFactory.getLogger(PensionProcessController.class);

	@PostMapping("/ProcessPension")
	@ResponseBody
	public ResponseEntity<PensionDetails> processPension(@RequestHeader("Authorization") final String idToken,
			@RequestBody ProcessPensionInput pinput) throws ResourceNotFoundException, UnAuthorizedRequestException {
		logger.info("In the api");

		Boolean isAuthorized = authProxy.onAuthorization(idToken).getBody();
		logger.info("After Auth PROXY " + isAuthorized);
		if (isAuthorized) {

			String aadhar = pinput.getAadharno();
			logger.info(aadhar);
			try {
				PensionerDetail pdetails = pdetailproxy.displayPensionerDetails(idToken, aadhar);
				logger.info(pdetails.toString());
				PensionDetails pensionDetails = processService.getPensionDetail(pdetails);
				PensionDetails pd = processService.savePensionDetail(pensionDetails);
				return ResponseEntity.ok(pd);
			} catch (Exception e) {
				logger.warn(e.getMessage());
				throw new ResourceNotFoundException();
			}

		} else {
			logger.info("Not Authorized");
			throw new UnAuthorizedRequestException("Not Authorized");
		}
	}

}
