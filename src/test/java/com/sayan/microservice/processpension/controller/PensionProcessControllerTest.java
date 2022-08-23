package com.sayan.microservice.processpension.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sayan.microservice.processpension.bean.PensionDetails;
import com.sayan.microservice.processpension.model.PensionerDetail;
import com.sayan.microservice.processpension.model.ProcessPensionInput;
import com.sayan.microservice.processpension.proxy.AuthProxy;
import com.sayan.microservice.processpension.proxy.PensionDetailProxy;
import com.sayan.microservice.processpension.service.ProcessPensionService;

@WebMvcTest(PensionProcessController.class)
public class PensionProcessControllerTest {
	@MockBean
    private ProcessPensionService pensionService;

   @MockBean
    private PensionDetailProxy pensionerDetailProxy;

   @MockBean
    private AuthProxy authProxy;

   @Autowired
    private MockMvc mockMvc;
   
   @Test
    void test_processPensionDetails() throws Exception {
        String aadhaarno = "123456789000";
        PensionerDetail pensionerDetailResponse = new PensionerDetail(aadhaarno, 0, null, null, null, null, 0, null);
        String token = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJzdWIiOiJSYW11MSIsImV4cCI6MTY1NzQyMzgxOSwiaWF0IjoxNjU3NDIyMDE5fQ.NxKTNWIWxerx1YacDzWZnpwVkXCO65fLthl8lQCQnyMozBvGjY9BEvHAsEbikkLwOnRmOoyuvowxDEI6HxoaQg";
        ResponseEntity<Boolean> authResp = new ResponseEntity<>(true, HttpStatus.OK);
        PensionDetails pensionDetailIn = new PensionDetails(aadhaarno,123,10);
        PensionDetails pensionDetailOut = pensionDetailIn;
        pensionDetailOut.setAadharno("100");
        ProcessPensionInput _pensionInput = new ProcessPensionInput(aadhaarno);
        String jsonObj = new ObjectMapper().writeValueAsString(_pensionInput);
        


        when(this.authProxy.onAuthorization(token)).thenReturn(authResp);
        when(this.pensionerDetailProxy.displayPensionerDetails(token, aadhaarno))
                .thenReturn(pensionerDetailResponse);
        when(this.pensionService.getPensionDetail(pensionerDetailResponse)).thenReturn(pensionDetailIn);
        when(this.pensionService.savePensionDetail(pensionDetailIn)).thenReturn(pensionDetailOut);

       this.mockMvc.perform(MockMvcRequestBuilders.post("/ProcessPension")
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObj)
                .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk());
   }

   @Test
    void test_processPensionDetails_for_exception_in_pensionerProxy() throws Exception {
        String aadhaarNumber = "123456789000";



       String token = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJzdWIiOiJSYW11MSIsImV4cCI6MTY1NzQyMzgxOSwiaWF0IjoxNjU3NDIyMDE5fQ.NxKTNWIWxerx1YacDzWZnpwVkXCO65fLthl8lQCQnyMozBvGjY9BEvHAsEbikkLwOnRmOoyuvowxDEI6HxoaQg";
        ResponseEntity<Boolean> authResp = new ResponseEntity<>(true, HttpStatus.OK);
        PensionDetails pensionDetailIn = new PensionDetails(
                aadhaarNumber,123,10);
        PensionDetails pensionDetailOut = pensionDetailIn;
        pensionDetailOut.setAadharno("100");
        ProcessPensionInput _pensionInput = new ProcessPensionInput(aadhaarNumber);
        String jsonObj = new ObjectMapper().writeValueAsString(_pensionInput);



       when(this.authProxy.onAuthorization(token)).thenReturn(authResp);
        when(this.pensionerDetailProxy.displayPensionerDetails(token, aadhaarNumber))
                .thenThrow(RuntimeException.class);


       this.mockMvc.perform(MockMvcRequestBuilders.post("/ProcessPension")
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObj)
                .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

   }

   @Test
    void test_processPensionDetails_for_invalid_token() throws Exception {
        String aadhaarNumber = "123456789000";
        String token = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJzdWIiOiJSYW11MSIsImV4cCI6MTY1NzQyMzgxOSwiaWF0IjoxNjU3NDIyMDE5fQ.NxKTNWIWxerx1YacDzWZnpwVkXCO65fLthl8lQCQnyMozBvGjY9BEvHAsEbikkLwOnRmOoyuvowxDEI6HxoaQg";
        ResponseEntity<Boolean> authResp = new ResponseEntity<>(false, HttpStatus.OK);

       ProcessPensionInput _pensionInput = new ProcessPensionInput(aadhaarNumber);
        String jsonObj = new ObjectMapper().writeValueAsString(_pensionInput);

       when(this.authProxy.onAuthorization(token)).thenReturn(authResp);

       this.mockMvc.perform(MockMvcRequestBuilders.post("/ProcessPension")
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObj)
                .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());

   }


}