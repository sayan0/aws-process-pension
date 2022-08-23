package com.sayan.microservice.processpension.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth", url = "${AUTH_MICROSERVICE_URI:http://sayan-lb-774609162.ap-south-1.elb.amazonaws.com}")
public interface AuthProxy {
	
	    @GetMapping("/auth/authorize")
	    public ResponseEntity<Boolean> onAuthorization(@RequestHeader("Authorization") String token);
	
}
