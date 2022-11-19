package de.tcg.jobFinder.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import de.tcg.jobFinder.entity.AppliedJob;

public interface AppliedJobService {
	public AppliedJob applyJob(HttpServletRequest request, String userId, String id);

	public boolean disApplyJob(HttpServletRequest request, String userId, String jobId);

	public boolean changeStatusRequestedJob(HttpServletRequest request, String businessId, String jobId, String status);

	public Map<String, Object> findAppliedJob(HttpServletRequest request, String id, int count, int page);
}
