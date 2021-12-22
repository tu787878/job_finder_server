package de.tcg.jobFinder.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.tcg.jobFinder.controller.JobApi;
import de.tcg.jobFinder.dto.JobQuerySearch;
import de.tcg.jobFinder.dto.SuccessResponse;
import de.tcg.jobFinder.entity.City;
import de.tcg.jobFinder.entity.Job;
import de.tcg.jobFinder.entity.JobCategory;
import de.tcg.jobFinder.entity.JobTag;

@RestController
@RequestMapping("/api/jobs")
public class JobApiImpl implements JobApi {

	@Autowired
	de.tcg.jobFinder.service.JobService jobService;
	

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
	public ResponseEntity<?> getJobs(@RequestParam(required = false) Map<String, String> restDTO) {
		
		JobQuerySearch jobQuerySearch = JobQuerySearch.toObject(restDTO);
		System.out.println(jobQuerySearch);
		Page<Job> jobPage = jobService.findAll(jobQuerySearch);
		
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("jobs", jobPage.getContent());
		
		if(jobQuerySearch.getCount() != 0) {
			res.put("totalPages", jobPage.getTotalPages());
			res.put("currentPage", jobPage.getNumber());
			res.put("totalCount", jobPage.getTotalElements());
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
}
