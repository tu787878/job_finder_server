package de.tcg.jobFinder.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
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

	@GetMapping(value = "", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getUsers(HttpServletRequest request);

	@GetMapping(value = "/:userId", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getUserById(HttpServletRequest request, @PathVariable(name = "userId") String userId);

	@PutMapping(value = "", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> updateUser(HttpServletRequest request,
			@RequestBody(required = false) UserRequest userRequest);
	
	@PostMapping(value = "", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> createUser(HttpServletRequest request,
			@RequestBody(required = false) UserRequest userRequest);

	@GetMapping(value = "/appliedJob", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getAppliedJob(HttpServletRequest request, @RequestParam(required = false) Map<String, String> restDTO);

}
