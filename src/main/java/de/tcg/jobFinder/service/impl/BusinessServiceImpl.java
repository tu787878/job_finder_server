package de.tcg.jobFinder.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import de.tcg.jobFinder.dto.SuccessResponse;
import de.tcg.jobFinder.entity.Account;
import de.tcg.jobFinder.entity.AccountToken;
import de.tcg.jobFinder.entity.Business;
import de.tcg.jobFinder.entity.BusinessCategory;
import de.tcg.jobFinder.entity.Job;
import de.tcg.jobFinder.reposity.BusinessCategoryReposity;
import de.tcg.jobFinder.reposity.BusinessReposity;
import de.tcg.jobFinder.reposity.CityReposity;
import de.tcg.jobFinder.reposity.JobReposity;
import de.tcg.jobFinder.service.AccountTokenService;
import de.tcg.jobFinder.service.BusinessService;
import de.tcg.jobFinder.service.MyUserDetailsService;

@Service
public class BusinessServiceImpl extends UntilService implements BusinessService {

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private AccountTokenService accountTokenService;

	@Autowired
	private BusinessReposity businessReposity;

	@Autowired
	private JobReposity jobReposity;
	
	@Autowired
	private CityReposity cityReposity;

	@Autowired
	private BusinessCategoryReposity businessCategoryReposity;

	@Override
	public ResponseEntity<?> getBusiness(HttpServletRequest request, String businessId) {
		String token = toToken(request);
		if (token != null) {
			AccountToken accountToken = accountTokenService.getAccountTokenByAccessToken(token);
			if (accountToken != null && accountToken.isActive()) {
				if (businessId == null) {
					Account account = myUserDetailsService.getAccountByAccountId(accountToken.getAccountId());
					businessId = account.getBusinessId();
				}
				Business business = businessReposity.findByBusinessId(businessId);
				Map<String, Object> res = new HashMap<String, Object>();

				res.put("business", business);
				return ResponseEntity.ok(new SuccessResponse(0, "success", res));
			}
		}

		return ResponseEntity.ok(new SuccessResponse(1, "failed", null));
	}

	@Override
	public ResponseEntity<?> getJobs(HttpServletRequest request, String businessId, boolean active) {
		String token = toToken(request);
		if (token != null) {
			AccountToken accountToken = accountTokenService.getAccountTokenByAccessToken(token);
			if (accountToken != null && accountToken.isActive()) {
				if (businessId == null) {
					Account account = myUserDetailsService.getAccountByAccountId(accountToken.getAccountId());
					businessId = account.getBusinessId();
				}
				List<Job> jobs = jobReposity.findByBusinessIdAndIsActive(businessId, active);
				Map<String, Object> res = new HashMap<String, Object>();

				res.put("jobs", jobs);
				return ResponseEntity.ok(new SuccessResponse(0, "success", res));
			}
		}

		return ResponseEntity.ok(new SuccessResponse(1, "failed", null));
	}

	@Override
	public ResponseEntity<?> getCategories(HttpServletRequest request, String businessId) {
		String token = toToken(request);
		if (token != null) {
			AccountToken accountToken = accountTokenService.getAccountTokenByAccessToken(token);
			if (accountToken != null && accountToken.isActive()) {
				if (businessId == null) {
					Account account = myUserDetailsService.getAccountByAccountId(accountToken.getAccountId());
					businessId = account.getBusinessId();
				}
				List<BusinessCategory> businessCategories = businessCategoryReposity.findAll();
				Map<String, Object> res = new HashMap<String, Object>();

				res.put("businessCategories", businessCategories);
				return ResponseEntity.ok(new SuccessResponse(0, "success", res));
			}
		}

		return ResponseEntity.ok(new SuccessResponse(1, "failed", null));
	}

	@Override
	public ResponseEntity<?> updateBusiness(HttpServletRequest request, Business business, String imageBase64) {
		String token = toToken(request);
		if (token != null) {
			AccountToken accountToken = accountTokenService.getAccountTokenByAccessToken(token);
			if (accountToken != null && accountToken.isActive()) {
				Account account = myUserDetailsService.getAccountByAccountId(accountToken.getAccountId());

				if (account.getBusinessId().equals(business.getbusinessId())) {
					if (imageBase64 != null) {
						try {
							// This will decode the String which is encoded by using Base64 class
//							byte[] imageByte = Base64.decodeBase64(imageBase64);
//
//							URL url = this.getClass().getClassLoader().getResource("/statics");
//							System.out.println(url.toString());
//							
//							OutputStream stream = new FileOutputStream("");
//							stream.write(imageByte);
							
							

//							String directory = request.getSession().getServletContext().getRealPath("/")
//									+ "images/sample.jpg";
//
//							if (!Files.exists(Paths.get(directory))) {
//								System.out.println("not exist");
//								Files.createFile(Paths.get(directory));
//							}
//
//							new FileOutputStream(directory).write(imageByte);
//
//							System.out.println(directory);
//
//							business.setbusinessLogoPath(directory);
//							businessReposity.save(business);
//							System.out.println(business);
							businessReposity.save(business);
							return ResponseEntity.ok(new SuccessResponse(0, "success", null));
						} catch (Exception e) {
							businessReposity.save(business);
							return ResponseEntity.ok(new SuccessResponse(0, e.toString(), null));
						}
					} else {
						businessReposity.save(business);
						return ResponseEntity.ok(new SuccessResponse(0, "success", null));
					}

				}
			}
		}

		return ResponseEntity.ok(new SuccessResponse(1, "failed", null));
	}

	@Override
	public Business getBusiness(String businessId) {
		return businessReposity.findByBusinessId(businessId);
	}

	@Override
	public BusinessCategory getCategoryById(String businessCategoryId) {
		return businessCategoryReposity.findByBusinessCategoryId(businessCategoryId);
	}

}
