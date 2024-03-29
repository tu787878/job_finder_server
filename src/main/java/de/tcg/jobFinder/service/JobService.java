package de.tcg.jobFinder.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;

import de.tcg.jobFinder.dto.JobQuerySearch;
import de.tcg.jobFinder.entity.City;
import de.tcg.jobFinder.entity.Job;
import de.tcg.jobFinder.entity.JobCategory;
import de.tcg.jobFinder.entity.JobTag;

public interface JobService {
	
	public boolean createExampleJob();
	
	public List<Job> getJobs(int limit, String orderBy, String orderType, String search);
	
	public List<JobCategory> getJobCategories();
	
	public List<City> getJobCities();
	
	public List<JobTag> getJobTags();

	public City getCityById(long cityId);

	public JobCategory getJobCategoryById(long jobCategoryId);

	public boolean newjob(HttpServletRequest request, String businessId, Job job);
	
	public boolean updateJob(HttpServletRequest request, String businessId, Job job);

	public JobTag getJobTagById(long jobTagsId);

	public Map<String, Object> findAll(HttpServletRequest request, JobQuerySearch jobQuerySearch);

	public Map<String, Object> findRelateJob(HttpServletRequest request, String id,  int count, int page);

	public Map<String, Object> findJobByBusiness(String businessid, int count, int page);

	public Map<String, Object> findByJobId(HttpServletRequest request, String jobId);

	public boolean deleteJob(HttpServletRequest request, String businessId, String jobId);

	public Job getjobByJobId(String jobId);
}
