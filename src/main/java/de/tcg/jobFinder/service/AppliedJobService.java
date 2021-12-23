package de.tcg.jobFinder.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import de.tcg.jobFinder.dto.ApplyJobRequest;

public interface AppliedJobService {
	public boolean applyJob(HttpServletRequest request, ApplyJobRequest applyJobRequest);

	public boolean disApplyJob(HttpServletRequest request, ApplyJobRequest applyJobRequest);

	public boolean changeStatusRequestedJob(HttpServletRequest request, ApplyJobRequest applyJobRequest);
	
	public Map<String, Object> findAppliedJob(HttpServletRequest request, int count, int page);
}
