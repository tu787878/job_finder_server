package de.tcg.jobFinder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/menu/web")
public interface WebMenuApi {

	@GetMapping("")
	public ResponseEntity<?> getVisibleMenu(@RequestParam(defaultValue = "vi-Vi", name = "lang") String lang,
			@RequestParam(defaultValue = "vietnam", name = "position") String position);

}
