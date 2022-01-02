package de.tcg.jobFinder.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tcg.jobFinder.controller.JobTagApi;
import de.tcg.jobFinder.dto.SuccessResponse;
import de.tcg.jobFinder.entity.JobTag;
import de.tcg.jobFinder.service.JobService;

@RestController
@RequestMapping("/api/jobTag")
public class JobTagApiImpl implements JobTagApi {

	@Autowired
	JobService jobService;

	@Override
	public ResponseEntity<?> getJobTags() {
		List<JobTag> tags = null;
		tags = jobService.getJobTags();

		if (tags != null) {
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("tags", tags);
			return ResponseEntity.ok(new SuccessResponse(0, "success", res));
		}

		return ResponseEntity.ok(new SuccessResponse(1, "failed", null));
	}

}
