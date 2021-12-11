package de.tcg.jobFinder.controller.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.tcg.jobFinder.controller.BusinessApi;
import de.tcg.jobFinder.dto.BusinessRequest;
import de.tcg.jobFinder.dto.JobRequest;
import de.tcg.jobFinder.dto.SuccessResponse;
import de.tcg.jobFinder.entity.Business;
import de.tcg.jobFinder.entity.BusinessCategory;
import de.tcg.jobFinder.entity.City;
import de.tcg.jobFinder.entity.Job;
import de.tcg.jobFinder.entity.JobCategory;
import de.tcg.jobFinder.entity.JobTag;
import de.tcg.jobFinder.service.BusinessService;
import de.tcg.jobFinder.service.JobService;

@RestController
@RequestMapping("/api/business")
public class BusinessApiImpl implements BusinessApi {

	@Autowired
	BusinessService businessService;

	@Autowired
	JobService jobService;

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

}
