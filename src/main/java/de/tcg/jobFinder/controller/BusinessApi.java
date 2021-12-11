package de.tcg.jobFinder.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.tcg.jobFinder.dto.BusinessRequest;
import de.tcg.jobFinder.dto.JobRequest;

@RequestMapping("/api/business")
public interface BusinessApi {

	@GetMapping(value = "", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getBusiness(HttpServletRequest request,
			@RequestParam(name = "businessId", required = false) String businessId);

	@PutMapping(value = "", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> updateBusiness(HttpServletRequest request,
			@RequestBody(required = true) BusinessRequest businessRequest);

	@GetMapping(value = "/jobs", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getJobs(HttpServletRequest request,
			@RequestParam(name = "businessId", required = false) String businessId);
	
	@PostMapping(value = "/jobs", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> newJob(HttpServletRequest request,
			@RequestBody(required = true) JobRequest jobRequest);

	@GetMapping(value = "/categories", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getCategories(HttpServletRequest request,
			@RequestParam(name = "businessId", required = false) String businessId);

}
