package com.sayan.microservice.processpension.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name="pensions_fetched_table")
public class PensionDetails {
	@Id
    private String aadharno;
	
	@Column(name = "pensionAmount")
	private float pensionAmount;
	
	@Column(name = "bankServiceCharge")
	private int bankServiceCharge;
	
}
