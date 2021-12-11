package de.tcg.jobFinder.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import de.tcg.jobFinder.dto.SuccessResponse;
import de.tcg.jobFinder.entity.Account;
import de.tcg.jobFinder.entity.AccountToken;
import de.tcg.jobFinder.entity.Business;
import de.tcg.jobFinder.entity.City;
import de.tcg.jobFinder.entity.Job;
import de.tcg.jobFinder.entity.JobCategory;
import de.tcg.jobFinder.entity.JobTag;
import de.tcg.jobFinder.reposity.BusinessReposity;
import de.tcg.jobFinder.reposity.CityReposity;
import de.tcg.jobFinder.reposity.JobCategoryReposity;
import de.tcg.jobFinder.reposity.JobReposity;
import de.tcg.jobFinder.reposity.JobTagReposity;
import de.tcg.jobFinder.service.AccountTokenService;
import de.tcg.jobFinder.service.JobService;
import de.tcg.jobFinder.service.MyUserDetailsService;

@Service
public class JobServiceImpl extends UntilService implements JobService {

	@Autowired
	JobReposity jobReposity;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	JobCategoryReposity jobCategoryReposity;

	@Autowired
	JobTagReposity jobTagReposity;

	@Autowired
	CityReposity cityReposity;

	@Autowired
	BusinessReposity businessReposity;

	@Autowired
	private AccountTokenService accountTokenService;

	@Override
	public boolean createExampleJob() {
		JobCategory jobCategory = jobCategoryReposity.getById((long) 6);
		City city = cityReposity.getById((long) 1);
		Set<JobTag> jobTags = new HashSet<JobTag>();
		jobTags.add(jobTagReposity.getById((long) 13));
		jobTags.add(jobTagReposity.getById((long) 14));
		Job job = new Job("Job_01", "Test Job", "mymycuisine", "", "Bad Birnbach", "jeden Tag", "", 0, 0, 15, 20, true,
				12345, LocalDateTime.now(), LocalDateTime.now().plusDays(2), jobCategory, city, "", "", jobTags);
		if (jobReposity.save(job) != null)
			return true;
		return false;
	}

	@Override
	public List<Map<String, Object>> getJobs(int limit, String orderBy, String orderType, String search) {
		List<Job> jobs = jobReposity.findAllWithQuerySearch(orderBy);
		List<Business> businesses = jobs.stream().map(e -> {
			return businessReposity.findByBusinessId(e.getBusinessId());
		}).toList();

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < jobs.size(); i++) {
			Job job = jobs.get(i);
			Business business = businesses.get(i);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("job", job);
			map.put("business", business);
			resultList.add(map);
		}

		return resultList;
	}

	@Override
	public List<JobCategory> getJobCategories() {
		List<JobCategory> jobCategories = jobCategoryReposity.findAll();
		return jobCategories;
	}

	@Override
	public List<City> getJobCities() {
		List<City> cities = cityReposity.findAll();
		return cities;
	}

	@Override
	public List<JobTag> getJobTags() {
		List<JobTag> tags = jobTagReposity.findAll();
		return tags;
	}

	@Override
	public City getCityById(long cityId) {
		return cityReposity.findById(cityId);
	}

	@Override
	public JobCategory getJobCategoryById(long jobCategoryId) {
		return jobCategoryReposity.findById(jobCategoryId);
	}

	@Override
	public boolean newjob(HttpServletRequest request, Job job) {
		String token = toToken(request);
		String businessId;
		if (token != null) {
			AccountToken accountToken = accountTokenService.getAccountTokenByAccessToken(token);
			if (accountToken != null && accountToken.isActive()) {
				Account account = myUserDetailsService.getAccountByAccountId(accountToken.getAccountId());
				businessId = account.getBusinessId();
				

				job.setBusinessId(businessId);
				job.setJobId("JOB_" + generateRandomId(10));
				System.out.println(job);
				jobReposity.save(job);
				return true;
			}
		}

		return false;
	}

	@Override
	public JobTag getJobTagById(long jobTagsId) {
		return jobTagReposity.findById(jobTagsId);
	}

}
