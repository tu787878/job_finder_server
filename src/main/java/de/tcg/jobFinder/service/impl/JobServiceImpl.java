package de.tcg.jobFinder.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import de.tcg.jobFinder.dto.JobQuerySearch;
import de.tcg.jobFinder.entity.Account;
import de.tcg.jobFinder.entity.AccountToken;
import de.tcg.jobFinder.entity.AppliedJob;
import de.tcg.jobFinder.entity.Business;
import de.tcg.jobFinder.entity.City;
import de.tcg.jobFinder.entity.Job;
import de.tcg.jobFinder.entity.JobCategory;
import de.tcg.jobFinder.entity.JobTag;
import de.tcg.jobFinder.entity.User;
import de.tcg.jobFinder.reposity.AppliedJobReposity;
import de.tcg.jobFinder.reposity.BusinessReposity;
import de.tcg.jobFinder.reposity.CityReposity;
import de.tcg.jobFinder.reposity.JobCategoryReposity;
import de.tcg.jobFinder.reposity.JobReposity;
import de.tcg.jobFinder.reposity.JobTagReposity;
import de.tcg.jobFinder.reposity.UserReposity;
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
	UserReposity userReposity;

	@Autowired
	AppliedJobReposity appliedJobReposity;

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
	public boolean newjob(HttpServletRequest request, String businessId, Job job) {
		String token = toToken(request);
		if (token != null) {
			AccountToken accountToken = accountTokenService.getAccountTokenByAccessToken(token);
			if (accountToken != null && accountToken.isActive()) {
				Account account = myUserDetailsService.getAccountByAccountId(accountToken.getAccountId());

				if (account.isBusiness() && account.getBusinessId().equals(businessId)) {
					Business business = businessReposity.findByBusinessId(businessId);

					job.setBusiness(business);

					String jobId;
					boolean flag = true;
					do {
						jobId = "JOB_" + account.getAccountId() + generateRandomId(5);
						flag = jobReposity.existsByJobId(jobId);
					} while (flag);

					job.setJobId(jobId);

					jobReposity.save(job);
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public JobTag getJobTagById(long jobTagsId) {
		return jobTagReposity.findById(jobTagsId);
	}

	@Override
	public Map<String, Object> findAll(HttpServletRequest request, JobQuerySearch jobQuerySearch) {
		String token = toToken(request);
		JobSpecification jobSpecification = new JobSpecification(jobQuerySearch);

		Page<Job> jobs = null;
		if (jobQuerySearch.getCount() != 0) {
			jobs = jobReposity.findAll(jobSpecification,
					PageRequest.of(jobQuerySearch.getPage(), jobQuerySearch.getCount()));
		} else {
			jobs = jobReposity.findAll(jobSpecification, Pageable.unpaged());
		}
		if (token != null && jobs != null) {
			AccountToken accountToken = accountTokenService.getAccountTokenByAccessToken(token);
			if (accountToken != null && accountToken.isActive()) {
				Account account = myUserDetailsService.getAccountByAccountId(accountToken.getAccountId());

				if (!account.isBusiness()) {
					User user = userReposity.findByUserId(account.getUserId());
					Map<String, Object> map = new HashMap<String, Object>();
					List<Map<String, Object>> list = additionalInformationJob(user, jobs.getContent(), true);
					map.put("jobs", list);
					map.put("totalPages", jobs.getTotalPages());
					map.put("currentPage", jobs.getNumber());
					map.put("totalCount", jobs.getTotalElements());
					return map;
				} else {
					Map<String, Object> map = new HashMap<String, Object>();
					List<Map<String, Object>> list = additionalInformationJob(null, jobs.getContent(), false);
					map.put("jobs", list);
					map.put("totalPages", jobs.getTotalPages());
					map.put("currentPage", jobs.getNumber());
					map.put("totalCount", jobs.getTotalElements());
					return map;
				}

			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = additionalInformationJob(null, jobs.getContent(), true);
		map.put("jobs", list);
		map.put("totalPages", jobs.getTotalPages());
		map.put("currentPage", jobs.getNumber());
		map.put("totalCount", jobs.getTotalElements());

		return map;
	}

	@Override
	public Map<String, Object> findRelateJob(HttpServletRequest request, String id, int count, int page) {
		String token = toToken(request);
		if (token != null) {
			AccountToken accountToken = accountTokenService.getAccountTokenByAccessToken(token);
			if (accountToken != null && accountToken.isActive()) {
				Account account = myUserDetailsService.getAccountByAccountId(accountToken.getAccountId());
				Map<String, Object> map = new HashMap<String, Object>();

				if (!account.isBusiness() && id.equals(account.getUserId())) {
					User user = userReposity.findByUserId(account.getUserId());
					// TODO: get jobCategories by User
					Set<JobCategory> jobCategories = userReposity.findByUserId(account.getUserId()).getJobCategories();

					// TODO: get job by jobCategories (with Page)
					Page<Job> jobs = null;
					if (count > 0) {
						jobs = jobReposity.findByJobCategoryIn(jobCategories, PageRequest.of(page, count));
					} else {
						jobs = jobReposity.findByJobCategoryIn(jobCategories, Pageable.unpaged());
					}

					List<Map<String, Object>> list = additionalInformationJob(user, jobs.getContent(), true);
					map.put("jobs", list);
					map.put("totalPages", jobs.getTotalPages());
					map.put("currentPage", jobs.getNumber());
					map.put("totalCount", jobs.getTotalElements());

					return map;
				} else {

					if (id.equals(account.getBusinessId())) {
						// TODO: get jobCategories from businessCategory from business
						List<JobCategory> jobCategories = jobCategoryReposity
								.findByBusinessCategoryId(businessReposity.findByBusinessId(account.getBusinessId())
										.getbusinessCategory().getBusinessCategoryId());

						// TODO: get job by jobCategories (with Page)
						Page<Job> jobs = null;
						if (count > 0) {
							jobs = jobReposity.findByJobCategoryIn(new HashSet<JobCategory>(jobCategories),
									PageRequest.of(page, count));
						} else {
							jobs = jobReposity.findByJobCategoryIn(new HashSet<JobCategory>(jobCategories),
									Pageable.unpaged());
						}

						List<Map<String, Object>> list = additionalInformationJob(null, jobs.getContent(), false);
						map.put("jobs", list);
						map.put("totalPages", jobs.getTotalPages());
						map.put("currentPage", jobs.getNumber());
						map.put("totalCount", jobs.getTotalElements());

						return map;
					}

				}

			}
		}

		return null;
	}

	private List<Map<String, Object>> additionalInformationJob(User user, List<Job> jobs, boolean isUser) {
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();

		if (isUser) {
			for (Job job : jobs) {

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("job", job);

				// TODO: count subscribers of each job
				long subscribers = appliedJobReposity.countByJobId(job.getId());
				map.put("subscribers", subscribers);

				// TODO: find all applied job with userId
				boolean isApplied = appliedJobReposity.existsByUserIdAndJobId(user, job.getId());
				map.put("isApplied", isApplied);
				if (isApplied) {
					AppliedJob status = appliedJobReposity.findByUserIdAndJobId(user, job.getId());
					map.put("statusApplied", status.getStatus());
				}

				// TODO: filter then
				maps.add(map);
			}
		} else {
			for (Job job : jobs) {
				Map<String, Object> map = new HashMap<String, Object>();

				map.put("job", job);

				// TODO: count subscribers of each job
				long subscribers = appliedJobReposity.countByJobId(job.getId());
				map.put("subscribers", subscribers);

				// TODO: find all applied job with userId
				boolean isApplied = false;
				map.put("isApplied", isApplied);
				if (isApplied) {
					AppliedJob status = appliedJobReposity.findByUserIdAndJobId(user, job.getId());
					map.put("statusApplied", status.getStatus());
				}

				// TODO: filter then
				maps.add(map);

			}

		}

		return maps;
	}

	@Override
	public Map<String, Object> findJobByBusiness(String businessid, int count, int page) {

		Business business = businessReposity.findByBusinessId(businessid);
		Map<String, Object> map = new HashMap<String, Object>();

		if (business != null) {
			Page<Job> jobs = null;
			if (count > 0) {
				jobs = jobReposity.findByBusiness(business, PageRequest.of(page, count));
			} else {
				jobs = jobReposity.findByBusiness(business, Pageable.unpaged());
			}

			List<Map<String, Object>> list = additionalInformationJob(null, jobs.getContent(), false);

			map.put("jobs", list);
			map.put("totalPages", jobs.getTotalPages());
			map.put("currentPage", jobs.getNumber());
			map.put("totalCount", jobs.getTotalElements());

			return map;
		} else {
			return null;
		}

	}

	@Override
	public Map<String, Object> findByJobId(HttpServletRequest request, String jobId) {

		String token = toToken(request);
		if (token != null) {
			AccountToken accountToken = accountTokenService.getAccountTokenByAccessToken(token);
			if (accountToken != null && accountToken.isActive()) {
				Account account = myUserDetailsService.getAccountByAccountId(accountToken.getAccountId());
				Map<String, Object> map = new HashMap<String, Object>();

				if (!account.isBusiness()) {
					User user = userReposity.findByUserId(account.getUserId());
					Job job = jobReposity.findByJobId(jobId);
					List<Job> jobs = new ArrayList<Job>();
					jobs.add(job);
					List<Map<String, Object>> list = additionalInformationJob(user, jobs, true);
					map.put("jobs", list.get(0));

					return map;
				} else {

					Job job = jobReposity.findByJobId(jobId);
					List<Job> jobs = new ArrayList<Job>();
					jobs.add(job);
					List<Map<String, Object>> list = additionalInformationJob(null, jobs, false);
					map.put("jobs", list.get(0));

					return map;
				}

			}

		}

		return null;
	}

	@Override
	public boolean deleteJob(HttpServletRequest request, String businessId, String jobId) {
		String token = toToken(request);
		if (token != null) {
			AccountToken accountToken = accountTokenService.getAccountTokenByAccessToken(token);
			if (accountToken != null && accountToken.isActive()) {
				Account account = myUserDetailsService.getAccountByAccountId(accountToken.getAccountId());

				if (account.isBusiness() && businessId.equals(account.getBusinessId())) {
					Job job = jobReposity.findByJobId(jobId);

					jobReposity.delete(job);

					return true;
				}
			}

		}

		return false;
	}
}
