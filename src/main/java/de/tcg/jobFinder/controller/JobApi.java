package de.tcg.jobFinder.controller;

import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.tcg.jobFinder.dto.ApplyJobRequest;

@RequestMapping("/api/jobs")
public interface JobApi {

	@GetMapping(value="/example",produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> createJob();

//	@GetMapping(value="",produces = "application/json;charset=UTF-8")
//	public ResponseEntity<?> getJobs(@RequestParam(name = "limit", required = true) int limit,
//			@RequestParam(name = "orderBy", required = true) String orderBy,
//			@RequestParam(name = "orderType", required = true) String orderType,
//			@RequestParam(name = "search", required = false, defaultValue = "") String search
//			);
	
	@GetMapping(value="",produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getJobs(HttpServletRequest request, @RequestParam(required = false) Map<String, String> restDTO);
	
	@GetMapping(value="/relate",produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getRelateJobs(HttpServletRequest request, @RequestParam(required = false) Map<String, String> restDTO);

	@GetMapping(value="/categories", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getCategories();
	
	@GetMapping(value="/cities",produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getCities();
	
	@GetMapping(value="/tags",produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getJobTags();
	
	@PostMapping(value="/apply",produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> applyJob(HttpServletRequest request, @RequestBody ApplyJobRequest applyJobRequest) throws MessagingException;
	
	@DeleteMapping(value="/disApply",produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> disApplyJob(HttpServletRequest request, @RequestBody ApplyJobRequest applyJobRequest);
	
}
