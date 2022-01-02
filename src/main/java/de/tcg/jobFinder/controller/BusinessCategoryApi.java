package de.tcg.jobFinder.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/businessCategory")
public interface BusinessCategoryApi {

	@GetMapping(value = "", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getBusiness(HttpServletRequest request);
}
