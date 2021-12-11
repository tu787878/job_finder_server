package de.tcg.jobFinder.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import de.tcg.jobFinder.entity.City;
import de.tcg.jobFinder.entity.Job;
import de.tcg.jobFinder.entity.JobCategory;
import de.tcg.jobFinder.entity.JobTag;

public interface JobService {
	
	public boolean createExampleJob();
	
	public List<Map<String,Object>> getJobs(int limit, String orderBy, String orderType, String search);
	
	public List<JobCategory> getJobCategories();
	
	public List<City> getJobCities();
	
	public List<JobTag> getJobTags();

	public City getCityById(long cityId);

	public JobCategory getJobCategoryById(long jobCategoryId);

	public boolean newjob(HttpServletRequest request, Job job);

	public JobTag getJobTagById(long jobTagsId);
}
