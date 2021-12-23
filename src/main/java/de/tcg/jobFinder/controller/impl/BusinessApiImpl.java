package de.tcg.jobFinder.controller.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.tcg.jobFinder.controller.BusinessApi;
import de.tcg.jobFinder.dto.ApplyJobRequest;
import de.tcg.jobFinder.dto.BusinessRequest;
import de.tcg.jobFinder.dto.JobRequest;
import de.tcg.jobFinder.dto.SuccessResponse;
import de.tcg.jobFinder.entity.Business;
import de.tcg.jobFinder.entity.BusinessCategory;
import de.tcg.jobFinder.entity.City;
import de.tcg.jobFinder.entity.Job;
import de.tcg.jobFinder.entity.JobCategory;
import de.tcg.jobFinder.entity.JobTag;
import de.tcg.jobFinder.service.AppliedJobService;
import de.tcg.jobFinder.service.BusinessService;
import de.tcg.jobFinder.service.JobService;

@RestController
@RequestMapping("/api/business")
public class BusinessApiImpl implements BusinessApi {

	@Autowired
	BusinessService businessService;

	@Autowired
	JobService jobService;
	
	@Autowired 
	AppliedJobService appliedJobService;

	@Override
	public ResponseEntity<?> getBusiness(HttpServletRequest request,
			@RequestParam(name = "businessId", required = false) String businessId) {

		return businessService.getBusiness(request, businessId);
	}

	@Override
	public ResponseEntity<?> getJobs(HttpServletRequest request,
			@RequestParam(name = "businessId", required = false) String businessId) {

		return businessService.getJobs(request, businessId, true);
	}

	@Override
	public ResponseEntity<?> getCategories(HttpServletRequest request,
			@RequestParam(name = "businessId", required = false) String businessId) {

		return businessService.getCategories(request, businessId);
	}

	@Override
	public ResponseEntity<?> updateBusiness(HttpServletRequest request,
			@RequestBody(required = true) BusinessRequest businessRequest) {
		Business business = businessService.getBusiness(businessRequest.getBusinessId());
		business.setbusinessAdress(businessRequest.getBusinessAddres());
		BusinessCategory businessCategory = businessService.getCategoryById(businessRequest.getBusinessCategoryId());
		business.setbusinessCategory(businessCategory);
		business.setbusinessDescription(businessRequest.getBusinessDescription());
		business.setbusinessName(businessRequest.getBusinessName());
		return businessService.updateBusiness(request, business, businessRequest.getImage());
	}

	@Override
	public ResponseEntity<?> newJob(HttpServletRequest request, JobRequest jobRequest) {
		System.out.println(jobRequest.toString());
		Job job = new Job();
		job.setJobName(jobRequest.getJobName());
		job.setJobAddress(jobRequest.getJobAddress());
		job.setJobDescription(jobRequest.getJobDescription());
		job.setJobRequirements(jobRequest.getJobRequirements());
		job.setJobBenefits(jobRequest.getJobBenefits());
		job.setCreatedTime(null);
		job.setExpiredTime(null);
		job.setActive(true);

		City city = jobService.getCityById(Long.valueOf(jobRequest.getCityId()));
		job.setCity(city);

		JobCategory jobCategory = jobService.getJobCategoryById(Long.valueOf(jobRequest.getJobCategoryId()));
		job.setJobCategory(jobCategory);

		job.setPostCode(Integer.valueOf(jobRequest.getPostCode()));
		job.setJobLatitude(0);
		job.setJobLongitude(0);
		job.setWorkingTime(jobRequest.getWorkingTime());
		job.setNote("");
		job.setSalaryFrom(Float.parseFloat(jobRequest.getSalaryFrom()));
		job.setSalaryTo(Float.parseFloat(jobRequest.getSalaryTo()));

		System.out.println(job.toString());
		
		List<JobTag> jobTags = new ArrayList<JobTag>();
		List<String> myList = new ArrayList<String>(Arrays.asList(jobRequest.getJobTagsId().split(",")));
		for (String l : myList) {
			JobTag tag = jobService.getJobTagById(Long.valueOf(l));
			jobTags.add(tag);
		}
		for(JobTag tag : jobTags) {
			System.out.println(tag.toString());
		}
		Set<JobTag> targetSet = Set.copyOf(jobTags);
		job.setJobTag(targetSet);
		
		

		if (jobService.newjob(request, job))
			return ResponseEntity.ok(new SuccessResponse(0, "success", null));

		return ResponseEntity.ok(new SuccessResponse(1, "fail", null));
	}

	@Override
	public ResponseEntity<?> changeStatusRequestedJob(HttpServletRequest request, ApplyJobRequest applyJobRequest) {
		boolean result = appliedJobService.changeStatusRequestedJob(request, applyJobRequest);
		if(result) return ResponseEntity.ok(new SuccessResponse(0, "success", null));
		return ResponseEntity.ok(new SuccessResponse(1, "faild", null));
	}
	
	@Override
	public ResponseEntity<?> getAppliedJob(HttpServletRequest request, @RequestParam(required = false) Map<String, String> restDTO) {
		int count = 0, page = 0;
		
		if(restDTO.get("count") != null && restDTO.get("page") != null) {
			count = Integer.valueOf(restDTO.get("count"));
			page = Integer.valueOf(restDTO.get("page"));
		}

		Map<String, Object> jobs = appliedJobService.findAppliedJob(request, count, page);

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

}
