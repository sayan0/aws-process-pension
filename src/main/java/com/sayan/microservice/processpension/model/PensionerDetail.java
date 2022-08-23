package com.sayan.microservice.processpension.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PensionerDetail {
	
	private String aadharno;
	private int allowances;
	private String bankdetails;
	private Date dob;
	private String name;
	private String pan;
	private int salary;
	private String type;
	
	public String getBankType() {
		String split[]= bankdetails.split(";");
		return split[2];
	}
}
