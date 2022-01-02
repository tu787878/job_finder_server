package de.tcg.jobFinder.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.tcg.jobFinder.dto.BusinessRequest;
import de.tcg.jobFinder.dto.JobRequest;

@RequestMapping("/api/business")
public interface BusinessApi {

	/*
	 * =============MANAGER BUSINESS==================================
	 * ===========================================================
	 */

	@PostMapping(value = "", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> createBusiness(HttpServletRequest request,
			@RequestBody(required = true) BusinessRequest businessRequest);

	@GetMapping(value = "/{businessId}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getBusiness(@PathVariable(name = "businessId") String businessId);

	@PutMapping(value = "/{businessId}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> updateBusiness(HttpServletRequest request,
			@PathVariable(name = "businessId") String businessId,
			@RequestBody(required = true) BusinessRequest businessRequest);

	@DeleteMapping(value = "/{businessId}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> deleteBusiness(HttpServletRequest request,
			@PathVariable(name = "businessId") String businessId);

	/*
	 * =============MANAGER JOBS BUSINESS==================================
	 * ===========================================================
	 */
	@GetMapping(value = "/{businessId}/jobs", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getJobs(@PathVariable(name = "businessId") String businessid,
			@RequestParam(required = false) Map<String, String> restDTO);

	@PostMapping(value = "/{businessId}/jobs", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> newJob(HttpServletRequest request, @PathVariable(name = "businessId") String businessId,
			@RequestBody(required = true) JobRequest jobRequest);

	@PutMapping(value = "/{businessId}/jobs/{jobId}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> editJob(HttpServletRequest request, @PathVariable(name = "businessId") String businessId,
			@PathVariable(name = "jobId") String jobId, @RequestBody(required = true) JobRequest jobRequest);

	@DeleteMapping(value = "/{businessId}/jobs/{jobId}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> deleteJob(HttpServletRequest request, @PathVariable(name = "businessId") String businessId,
			@PathVariable(name = "jobId") String jobId);

	/*
	 * =============MANAGER APPLIED JOBS BUSINESS==================================
	 * ===========================================================
	 */
	@GetMapping(value = "/{businessId}/appliedJob", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getAppliedJob(HttpServletRequest request,
			@PathVariable(name = "businessId") String businessId,
			@RequestParam(required = false) Map<String, String> restDTO);

	@PutMapping(value = "/{businessId}/appliedJob/{jobId}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> changeStatusRequestedJob(HttpServletRequest request,
			@PathVariable(name = "businessId") String businessId, @PathVariable(name = "jobId") String jobId,
			@RequestParam(name = "status", required = true) String status);

	/*
	 * =============MANAGER RELATE JOBS BUSINESS==================================
	 * ===========================================================
	 */
	@GetMapping(value = "/{businessId}/relateJob", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getRelateJobs(HttpServletRequest request,
			@PathVariable(name = "businessId") String businessId,
			@RequestParam(required = false) Map<String, String> restDTO);
}
