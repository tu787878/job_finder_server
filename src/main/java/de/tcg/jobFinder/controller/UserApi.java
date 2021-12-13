package de.tcg.jobFinder.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import de.tcg.jobFinder.dto.UserRequest;

@RequestMapping("/api/users")
public interface UserApi {

	@GetMapping(value = "", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getUsers(HttpServletRequest request);

	@GetMapping(value = "/:userId", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getUserById(HttpServletRequest request, @PathVariable(name = "userId") String userId);

	@PutMapping(value = "/:userId", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> updateUserById(HttpServletRequest request, @PathVariable(name = "userId") String userId,
			@RequestBody(required = true) UserRequest userRequest);

}
