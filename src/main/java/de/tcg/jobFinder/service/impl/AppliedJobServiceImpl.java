package de.tcg.jobFinder.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import de.tcg.jobFinder.dto.ApplyJobRequest;
import de.tcg.jobFinder.entity.Account;
import de.tcg.jobFinder.entity.AccountToken;
import de.tcg.jobFinder.entity.AppliedJob;
import de.tcg.jobFinder.entity.Job;
import de.tcg.jobFinder.entity.User;
import de.tcg.jobFinder.reposity.AppliedJobReposity;
import de.tcg.jobFinder.reposity.JobReposity;
import de.tcg.jobFinder.reposity.UserReposity;
import de.tcg.jobFinder.service.AccountTokenService;
import de.tcg.jobFinder.service.AppliedJobService;
import de.tcg.jobFinder.service.MyUserDetailsService;

@Service
public class AppliedJobServiceImpl extends UntilService implements AppliedJobService {

	@Autowired
	UserReposity userReposity;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private AccountTokenService accountTokenService;

	@Autowired
	private JobReposity jobReposity;

	@Autowired
	private AppliedJobReposity appliedJobReposity;

	@Override
	public boolean applyJob(HttpServletRequest request, ApplyJobRequest applyJobRequest) {
		String token = toToken(request);
		if (token != null) {
			AccountToken accountToken = accountTokenService.getAccountTokenByAccessToken(token);
			if (accountToken != null && accountToken.isActive()) {

				Account account = myUserDetailsService.getAccountByAccountId(accountToken.getAccountId());
		

				if (!account.isBusiness()) {
					User user = userReposity.findByUserId(account.getUserId());
					Job job = jobReposity.findByJobId(applyJobRequest.getJobId());

					if (job != null) {
						boolean existAppliedJob = appliedJobReposity.existsByUserIdAndJobId(user,
								job.getId());

						if (!existAppliedJob) {

							AppliedJob appliedJob = new AppliedJob(user, job.getBusiness().getbusinessId(), job, "request", LocalDateTime.now());
							appliedJob = appliedJobReposity.save(appliedJob);

							return true;
						}
					}

					return false;

				}
			}
		}

		return false;
	}

	@Override
	public boolean disApplyJob(HttpServletRequest request, ApplyJobRequest applyJobRequest) {
		String token = toToken(request);
		if (token != null) {
			AccountToken accountToken = accountTokenService.getAccountTokenByAccessToken(token);
			if (accountToken != null && accountToken.isActive()) {

				Account account = myUserDetailsService.getAccountByAccountId(accountToken.getAccountId());

				if (!account.isBusiness()) {
					User user = userReposity.findByUserId(account.getUserId());
					Job job = jobReposity.findByJobId(applyJobRequest.getJobId());

					if (job != null) {
						boolean existAppliedJob = appliedJobReposity.existsByUserIdAndJobId(user,
								job.getId());

						if (existAppliedJob) {
							AppliedJob appliedJob = appliedJobReposity.findByUserIdAndJobId(user,
									job.getId());
							appliedJobReposity.delete(appliedJob);
							return true;
						}
					}

					return false;
				}
			}
		}

		return false;
	}

	@Override
	public boolean changeStatusRequestedJob(HttpServletRequest request, ApplyJobRequest applyJobRequest) {
		String token = toToken(request);
		if (token != null) {
			AccountToken accountToken = accountTokenService.getAccountTokenByAccessToken(token);
			if (accountToken != null && accountToken.isActive()) {

				Account account = myUserDetailsService.getAccountByAccountId(accountToken.getAccountId());

				if (account.isBusiness()) {
					User user = userReposity.findByUserId(account.getUserId());
					Job job = jobReposity.findByJobId(applyJobRequest.getJobId());

					if (job != null && job.getBusiness().getbusinessId().equals(account.getBusinessId())) {
						boolean existAppliedJob = appliedJobReposity.existsByUserIdAndJobId(user,
								job.getId());

						if (existAppliedJob) {
							AppliedJob appliedJob = appliedJobReposity.findByUserIdAndJobId(user,
									job.getId());
							appliedJob.setStatus(applyJobRequest.getStatus());
							appliedJobReposity.save(appliedJob);
							return true;
						}
					}

					return false;
				}
			}
		}

		return false;
	}
	
	@Override
	public Map<String, Object> findAppliedJob(HttpServletRequest request, int count, int page) {
		String token = toToken(request);
		if (token != null) {
			AccountToken accountToken = accountTokenService.getAccountTokenByAccessToken(token);
			if (accountToken != null && accountToken.isActive()) {
				Account account = myUserDetailsService.getAccountByAccountId(accountToken.getAccountId());
				Map<String, Object> map = new HashMap<String, Object>();

				if (!account.isBusiness()) {
					User user = userReposity.findByUserId(account.getUserId());
					Page<AppliedJob> jobs = null;
					if (count > 0) {
						jobs = appliedJobReposity.findByUserId(user, PageRequest.of(page, count));
					} else {
						jobs = appliedJobReposity.findByUserId(user, Pageable.unpaged());
					}

					if(jobs != null) {
						System.out.println(jobs);
						map.put("jobs", jobs.getContent());
						map.put("totalPages", jobs.getTotalPages());
						map.put("currentPage", jobs.getNumber());
						map.put("totalCount", jobs.getTotalElements());
					}

					return map;
				} else {

					Page<AppliedJob> jobs = null;
					if (count > 0) {
						jobs = appliedJobReposity.findByBusinessId(account.getBusinessId(), PageRequest.of(page, count));
					} else {
						jobs = appliedJobReposity.findByBusinessId(account.getBusinessId(), Pageable.unpaged());
					}

					if(jobs != null) {
						map.put("jobs", jobs.getContent());
						map.put("totalPages", jobs.getTotalPages());
						map.put("currentPage", jobs.getNumber());
						map.put("totalCount", jobs.getTotalElements());
					}

					return map;
				}

			}
		}

		return null;
	}

}
