package de.tcg.jobFinder.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/jobs")
public interface JobApi {

	@GetMapping(value = "", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getJobs(HttpServletRequest request,
			@RequestParam(required = false) Map<String, String> restDTO);

	@GetMapping(value = "/{jobId}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getJobByJobId(HttpServletRequest request, @PathVariable(name = "jobId") String jobId);

}
