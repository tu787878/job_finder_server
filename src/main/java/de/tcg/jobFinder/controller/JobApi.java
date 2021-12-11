package de.tcg.jobFinder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/jobs")
public interface JobApi {

	@GetMapping(value="/example",produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> createJob();

	@GetMapping(value="",produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getJobs(@RequestParam(name = "limit", required = true) int limit,
			@RequestParam(name = "orderBy", required = true) String orderBy,
			@RequestParam(name = "orderType", required = true) String orderType,
			@RequestParam(name = "search", required = false, defaultValue = "") String search
			);

	@GetMapping(value="/categories", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getCategories();
	
	@GetMapping(value="/cities",produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getCities();
	
	@GetMapping(value="/tags",produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getJobTags();
}
