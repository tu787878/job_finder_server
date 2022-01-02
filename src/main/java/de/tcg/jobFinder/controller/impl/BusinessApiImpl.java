package de.tcg.jobFinder.controller.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.tcg.jobFinder.controller.BusinessApi;
import de.tcg.jobFinder.dto.BusinessRequest;
import de.tcg.jobFinder.dto.JobRequest;
import de.tcg.jobFinder.dto.SuccessResponse;
import de.tcg.jobFinder.entity.Account;
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

	/*
	 * =============MANAGER BUSINESS==================================
	 * ===========================================================
	 */
	@Override
	public ResponseEntity<?> getBusiness(@PathVariable(name = "businessId") String businessId) {

		Business business = businessService.getBusiness(businessId);

		if (business != null) {
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("business", business);
			return ResponseEntity.ok(new SuccessResponse(0, "success", res));
		}
		return ResponseEntity.ok(new SuccessResponse(1, "fail", null));
	}

	@Override
	public ResponseEntity<?> updateBusiness(HttpServletRequest request,
			@PathVariable(name = "businessId") String businessid,
			@RequestBody(required = true) BusinessRequest businessRequest) {
		Business business = businessService.getBusiness(businessid);
		business.setbusinessAddress(businessRequest.getBusinessAddress());
		BusinessCategory businessCategory = businessService.getCategoryById(businessRequest.getBusinessCategoryId());
		business.setbusinessCategory(businessCategory);
		business.setbusinessDescription(businessRequest.getBusinessDescription());
		business.setbusinessName(businessRequest.getBusinessName());
		System.out.println(business);

		boolean check = businessService.updateBusiness(request, business, businessRequest.getImage());
		if (check) {
			return ResponseEntity.ok(new SuccessResponse(0, "success", null));
		}
		return ResponseEntity.ok(new SuccessResponse(1, "fail", null));
	}

	@Override
	public ResponseEntity<?> deleteBusiness(HttpServletRequest request, String businessId) {
		boolean check = businessService.deleteBusiness(request, businessId);
		if (check) {
			return ResponseEntity.ok(new SuccessResponse(0, "success", null));
		}
		return ResponseEntity.ok(new SuccessResponse(1, "fail", null));
	}

	@Override
	public ResponseEntity<?> createBusiness(HttpServletRequest request, BusinessRequest businessRequest) {
		Business business = new Business();
		business.setbusinessAddress(businessRequest.getBusinessAddress());
		BusinessCategory businessCategory = businessService.getCategoryById(businessRequest.getBusinessCategoryId());
		business.setbusinessCategory(businessCategory);
		business.setbusinessDescription(businessRequest.getBusinessDescription());
		business.setbusinessName(businessRequest.getBusinessName());

		Account account = businessService.createBusiness(request, business, businessRequest.getImage());
		if (account != null) {
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("account", account);
			return ResponseEntity.ok(new SuccessResponse(0, "success", res));
		}
		return ResponseEntity.ok(new SuccessResponse(0, "fail", null));
	}

	/*
	 * =============MANAGER JOBS BUSINESS==================================
	 * ===========================================================
	 */
	@Override
	public ResponseEntity<?> getJobs(@PathVariable(name = "businessId") String businessid,
			@RequestParam(required = false) Map<String, String> restDTO) {

		int count = 0, page = 0;

		if (restDTO.get("count") != null && restDTO.get("page") != null) {
			count = Integer.valueOf(restDTO.get("count"));
			page = Integer.valueOf(restDTO.get("page"));
		}

		Map<String, Object> jobs = jobService.findJobByBusiness(businessid, count, page);

		if (jobs != null) {
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

		return ResponseEntity.ok(new SuccessResponse(1, "fail", null));
	}

	@Override
	public ResponseEntity<?> newJob(HttpServletRequest request, String businessId, JobRequest jobRequest) {
		Job job = new Job();
		job.setJobName(jobRequest.getJobName());
		job.setJobAddress(jobRequest.getJobAddress());
		job.setJobDescription(jobRequest.getJobDescription());
		job.setJobRequirements(jobRequest.getJobRequirements());
		job.setJobBenefits(jobRequest.getJobBenefits());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		job.setCreatedTime(LocalDateTime.parse(jobRequest.getCreatedTime(), formatter));
		job.setExpiredTime(LocalDateTime.parse(jobRequest.getExpiredTime(), formatter));
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

//		System.out.println(job.toString());

		List<JobTag> jobTags = new ArrayList<JobTag>();
		List<String> myList = new ArrayList<String>(Arrays.asList(jobRequest.getJobTagsId().split(",")));
		for (String l : myList) {
			JobTag tag = jobService.getJobTagById(Long.valueOf(l));
			jobTags.add(tag);
		}
//		for(JobTag tag : jobTags) {
//			System.out.println(tag.toString());
//		}
		Set<JobTag> targetSet = Set.copyOf(jobTags);
		job.setJobTag(targetSet);

		if (jobService.newjob(request, businessId, job))
			return ResponseEntity.ok(new SuccessResponse(0, "success", null));

		return ResponseEntity.ok(new SuccessResponse(1, "fail", null));
	}

	@Override
	public ResponseEntity<?> editJob(HttpServletRequest request, String businessId, String jobId, JobRequest jobRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> deleteJob(HttpServletRequest request, String businessId, String jobId) {
		boolean result = jobService.deleteJob(request, businessId, jobId);
		if (result)
			return ResponseEntity.ok(new SuccessResponse(0, "success", null));
		return ResponseEntity.ok(new SuccessResponse(1, "faild", null));
	}

	/*
	 * =============MANAGER APPLIED JOBS BUSINESS==================================
	 * ===========================================================
	 */
	@Override
	public ResponseEntity<?> changeStatusRequestedJob(HttpServletRequest request, String businessId, String jobId,
			String status) {
		boolean result = appliedJobService.changeStatusRequestedJob(request, businessId, jobId, status);
		if (result)
			return ResponseEntity.ok(new SuccessResponse(0, "success", null));
		return ResponseEntity.ok(new SuccessResponse(1, "faild", null));
	}

	@Override
	public ResponseEntity<?> getAppliedJob(HttpServletRequest request,
			@PathVariable(name = "businessId") String businessId,
			@RequestParam(required = false) Map<String, String> restDTO) {
		int count = 0, page = 0;

		if (restDTO.get("count") != null && restDTO.get("page") != null) {
			count = Integer.valueOf(restDTO.get("count"));
			page = Integer.valueOf(restDTO.get("page"));
		}

		Map<String, Object> jobs = appliedJobService.findAppliedJob(request, businessId, count, page);

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

	/*
	 * =============MANAGER RELATE JOBS BUSINESS==================================
	 * ===========================================================
	 */
	@Override
	public ResponseEntity<?> getRelateJobs(HttpServletRequest request, String businessId, Map<String, String> restDTO) {
		int count = 0, page = 0;

		if (restDTO.get("count") != null && restDTO.get("page") != null) {
			count = Integer.valueOf(restDTO.get("count"));
			page = Integer.valueOf(restDTO.get("page"));
		}

		Map<String, Object> jobs = jobService.findRelateJob(request, businessId, count, page);

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
