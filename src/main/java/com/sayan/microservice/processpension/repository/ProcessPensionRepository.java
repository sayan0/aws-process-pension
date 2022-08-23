package com.sayan.microservice.processpension.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sayan.microservice.processpension.bean.PensionDetails;


@Repository
public interface ProcessPensionRepository extends JpaRepository<PensionDetails, String> {
	
}
