package de.tcg.jobFinder.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.tcg.jobFinder.controller.JobApi;
import de.tcg.jobFinder.dto.ApplyJobRequest;
import de.tcg.jobFinder.dto.JobQuerySearch;
import de.tcg.jobFinder.dto.SuccessResponse;
import de.tcg.jobFinder.entity.City;
import de.tcg.jobFinder.entity.Job;
import de.tcg.jobFinder.entity.JobCategory;
import de.tcg.jobFinder.entity.JobTag;
import de.tcg.jobFinder.service.AppliedJobService;

@RestController
@RequestMapping("/api/jobs")
public class JobApiImpl implements JobApi {

	@Autowired
	de.tcg.jobFinder.service.JobService jobService;

	@Autowired
	AppliedJobService appliedJobService;

	@Override
	public ResponseEntity<?> createJob() {
		if (jobService.createExampleJob())
			return ResponseEntity.ok(new SuccessResponse(0, "success", null));
		return ResponseEntity.ok(new SuccessResponse(1, "failed", null));
	}

//	@Override
//	public ResponseEntity<?> getJobs(@RequestParam JobQuerySearch jobQuerySearch) {
//		
//		
//		List<Job> jobs = jobService.getJobs(limit, orderBy, orderType, search);
//		
//		if (jobs != null) {
//			Map<String, Object> res = new HashMap<String, Object>();
//			res.put("jobs", jobs);
//			return ResponseEntity.ok(new SuccessResponse(0, "success", res));
//		}
//			
//		return ResponseEntity.ok(new SuccessResponse(1, "failed", null));
//	}

	@Override
	public ResponseEntity<?> getJobs(HttpServletRequest request,
			@RequestParam(required = false) Map<String, String> restDTO) {

		JobQuerySearch jobQuerySearch = JobQuerySearch.toObject(restDTO);
		// System.out.println(jobQuerySearch);
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
	public ResponseEntity<?> getCategories() {
		List<JobCategory> jobCategories = null;
		jobCategories = jobService.getJobCategories();

		if (jobCategories != null) {
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("jobCategories", jobCategories);
			return ResponseEntity.ok(new SuccessResponse(0, "success", res));
		}

		return ResponseEntity.ok(new SuccessResponse(1, "failed", null));
	}

	@Override
	public ResponseEntity<?> getCities() {
		List<City> cities = null;
		cities = jobService.getJobCities();

		if (cities != null) {
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("cities", cities);
			return ResponseEntity.ok(new SuccessResponse(0, "success", res));
		}

		return ResponseEntity.ok(new SuccessResponse(1, "failed", null));
	}

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

	@Override
	public ResponseEntity<?> getRelateJobs(HttpServletRequest request, Map<String, String> restDTO) {
		int count = 0, page = 0;

		if (restDTO.get("count") != null && restDTO.get("page") != null) {
			count = Integer.valueOf(restDTO.get("count"));
			page = Integer.valueOf(restDTO.get("page"));
		}

		Map<String, Object> jobs = jobService.findRelateJob(request, count, page);

		Map<String, Object> res = new HashMap<String, Object>();
		res.put("jobs", jobs.get("jobs"));

		if (count != 0) {
			res.put("totalPages", jobs.get("totalPages"));
			res.put("currentPage", jobs.get("currentPage"));
			res.put("totalCount", jobs.get("totalCount"));
			res.put("countPerPage", count);
		}

		return ResponseEntity.ok(new SuccessResponse(0, "success", res));
	}

	@Override
	public ResponseEntity<?> applyJob(HttpServletRequest request, ApplyJobRequest applyJobRequest) {
		boolean result = appliedJobService.applyJob(request, applyJobRequest);
		if (result)
			return ResponseEntity.ok(new SuccessResponse(0, "success", null));
		return ResponseEntity.ok(new SuccessResponse(1, "faild", null));
	}

	@Override
	public ResponseEntity<?> disApplyJob(HttpServletRequest request, ApplyJobRequest applyJobRequest) {
		boolean result = appliedJobService.disApplyJob(request, applyJobRequest);
		if (result)
			return ResponseEntity.ok(new SuccessResponse(0, "success", null));
		return ResponseEntity.ok(new SuccessResponse(1, "faild", null));
	}
}
