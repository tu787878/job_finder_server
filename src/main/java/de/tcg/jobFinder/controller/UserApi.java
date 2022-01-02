package de.tcg.jobFinder.controller;

import java.util.Map;

import javax.mail.MessagingException;
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

import de.tcg.jobFinder.dto.UserRequest;

@RequestMapping("/api/user")
public interface UserApi {
	/*
	 * all user
	 * 
	 */
	// get all user [GET]
	@GetMapping(value = "", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getUsers();

	/*
	 * =============MANAGER USER==================================
	 * ===========================================================
	 */
	@PostMapping(value = "", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> createUser(HttpServletRequest request,
			@RequestBody(required = false) UserRequest userRequest);

	@GetMapping(value = "/{userId}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getUserById(@PathVariable(name = "userId") String userId);

	@PutMapping(value = "/{userId}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> updateUser(HttpServletRequest request,
			@RequestBody(required = false) UserRequest userRequest, @PathVariable(name = "userId") String userId);

	@DeleteMapping(value = "/{userId}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> deleteUser(HttpServletRequest request, @PathVariable(name = "userId") String userId);

	/*
	 * =============MANAGER APPLIED JOB==================================
	 * ===========================================================
	 */
	@GetMapping(value = "/{userId}/appliedJob", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getAppliedJob(HttpServletRequest request,
			@RequestParam(required = false) Map<String, String> restDTO, @PathVariable(name = "userId") String userId);

	@PostMapping(value = "/{userId}/appliedJob/{jobId}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> applyJob(HttpServletRequest request,
			@RequestParam(required = false) Map<String, String> restDTO, @PathVariable(name = "userId") String userId,
			@PathVariable(name = "jobId") String jobId) throws MessagingException;

	@DeleteMapping(value = "/{userId}/appliedJob/{jobId}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> deleteAppliedJob(HttpServletRequest request, @PathVariable(name = "userId") String userId,
			@PathVariable(name = "jobId") String jobId);

	/*
	 * =============RELATE JOB==================================
	 * ===========================================================
	 */

	@GetMapping(value = "/{userId}/relateJob", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getRelateJobs(HttpServletRequest request, @PathVariable(name = "userId") String userId,
			@RequestParam(required = false) Map<String, String> restDTO);
}
