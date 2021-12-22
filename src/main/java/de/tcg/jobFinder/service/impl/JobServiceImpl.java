package de.tcg.jobFinder.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import de.tcg.jobFinder.dto.JobQuerySearch;
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
import de.tcg.jobFinder.specification.JobSpecification;

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

		return false;
	}

	@Override
	public List<Job> getJobs(int limit, String orderBy, String orderType, String search) {
		List<Job> jobs = jobReposity.findAllWithQuerySearch(orderBy);

		return jobs;
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
				Business business = businessReposity.findByBusinessId(businessId);

				job.setBusiness(business);
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

	@Override
	public Page<Job> findAll(JobQuerySearch jobQuerySearch) {
		JobSpecification jobSpecification =  new JobSpecification(jobQuerySearch);
		if(jobQuerySearch.getCount() != 0) {
			return jobReposity.findAll(jobSpecification, PageRequest.of(jobQuerySearch.getPage(), jobQuerySearch.getCount()));
		}
		return jobReposity.findAll(jobSpecification, Pageable.unpaged());
	}

}
