package de.tcg.jobFinder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/jobCity")
public interface JobCityApi {

	/*
	 * =============MANAGER JOB CITY==================================
	 * ===========================================================
	 */
	@GetMapping(value="",produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getJobCities();
	
}
