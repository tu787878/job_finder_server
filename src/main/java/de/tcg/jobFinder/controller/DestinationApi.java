package de.tcg.jobFinder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/destinations")
public interface DestinationApi {
	
	@GetMapping("")
	public ResponseEntity<?> getAll(@RequestParam(defaultValue = "vietnam", name = "position") String position);

	
}
