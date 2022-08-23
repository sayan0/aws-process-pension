package com.sayan.microservice.processpension.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sayan.microservice.processpension.model.PensionerDetail;

@FeignClient(name = "pensioner-detail", url = "${PENSIONER_DETAIL_MICROSERVICE_URI:http://sayan-lb-774609162.ap-south-1.elb.amazonaws.com}")
public interface PensionDetailProxy {
	
	@GetMapping("/pensioner-detail/PensionerDetailByAadhaar/{aadharno}")
	@ResponseBody
    public PensionerDetail displayPensionerDetails(
    		@RequestHeader("Authorization") String token, 
    		@PathVariable String aadharno);
}
