package de.tcg.jobFinder.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tcg.jobFinder.controller.JobCategoryApi;
import de.tcg.jobFinder.dto.SuccessResponse;
import de.tcg.jobFinder.entity.JobCategory;
import de.tcg.jobFinder.service.JobService;

@RestController
@RequestMapping("/api/jobCategory")
public class JobCategoryApiImpl implements JobCategoryApi {

	@Autowired
	JobService jobService;

	@Override
	public ResponseEntity<?> getJobCategories() {
		List<JobCategory> jobCategories = null;
		jobCategories = jobService.getJobCategories();

		if (jobCategories != null) {
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("jobCategories", jobCategories);
			return ResponseEntity.ok(new SuccessResponse(0, "success", res));
		}

		return ResponseEntity.ok(new SuccessResponse(1, "failed", null));
	}

}
