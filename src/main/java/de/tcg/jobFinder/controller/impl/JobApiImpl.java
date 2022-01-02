package de.tcg.jobFinder.controller.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.tcg.jobFinder.controller.JobApi;
import de.tcg.jobFinder.dto.JobQuerySearch;
import de.tcg.jobFinder.dto.SuccessResponse;

@RestController
@RequestMapping("/api/jobs")
public class JobApiImpl implements JobApi {

	@Autowired
	de.tcg.jobFinder.service.JobService jobService;

	@Override
	public ResponseEntity<?> getJobs(HttpServletRequest request,
			@RequestParam(required = false) Map<String, String> restDTO) {

		JobQuerySearch jobQuerySearch = JobQuerySearch.toObject(restDTO);
		Map<String, Object> jobs = jobService.findAll(request, jobQuerySearch);

		Map<String, Object> res = new HashMap<String, Object>();
		res.put("jobs", jobs.get("jobs"));

		if (jobQuerySearch.getCount() != 0) {
			res.put("totalPages", jobs.get("totalPages"));
			res.put("currentPage", jobs.get("currentPage"));
			res.put("totalCount", jobs.get("totalCount"));
			res.put("countPerPage", jobQuerySearch.getCount());
		}

		return ResponseEntity.ok(new SuccessResponse(0, "success", res));
	}

	@Override
	public ResponseEntity<?> getJobByJobId(HttpServletRequest request, String jobId) {
		Map<String, Object> jobResponse = jobService.findByJobId(request, jobId);

		Map<String, Object> res = new HashMap<String, Object>();
		res.put("jobs", jobResponse.get("jobs"));
		return null;
	}

}
