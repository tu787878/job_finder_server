package de.tcg.jobFinder.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import de.tcg.jobFinder.dto.PathId;
import de.tcg.jobFinder.dto.SuccessResponse;
import de.tcg.jobFinder.entity.Account;
import de.tcg.jobFinder.entity.AccountToken;
import de.tcg.jobFinder.entity.Business;
import de.tcg.jobFinder.entity.BusinessCategory;
import de.tcg.jobFinder.entity.Job;
import de.tcg.jobFinder.reposity.AccountReposity;
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
	private AccountReposity accountReposity;

	@Autowired
	private BusinessCategoryReposity businessCategoryReposity;

	@Value("${media.path}")
	String basisPath;

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
							// save logo
							System.out.println(account.getBusinessId());
							System.out.println(imageBase64.split(",").length);
							String pathString = basisPath + "/image" + PathId.upload.getPath();

							byte[] data = Base64.getDecoder().decode(imageBase64.getBytes(StandardCharsets.UTF_8));
							OutputStream stream;

							String imageName = "/b" + account.getBusinessId().split("_")[1] + ".png";

							try {
								stream = new FileOutputStream(pathString + imageName);
								stream.write(data);
							} catch (IOException e) {
								System.out.println(e);
							}

							String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null)
									.build().toUriString();

							business.setbusinessLogoPath(baseUrl + "/media/image/upload" + imageName);
							// System.out.println("http://localhost:8080/media/image/upload" + imageName);
							System.out.println(business);
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

	@Override
	public Account createBusiness(HttpServletRequest request, Business business, String image) {
		String token = toToken(request);
		if (token != null) {
			AccountToken accountToken = accountTokenService.getAccountTokenByAccessToken(token);
			if (accountToken != null && accountToken.isActive()) {
				Account account = myUserDetailsService.getAccountByAccountId(accountToken.getAccountId());

				if (account.isBusiness()) {
					if (account.getBusinessId().equals("")) {
						String businessId = "";
						int count = 0;
						boolean flag = true;
						do {
							businessId = "BUSINESS_" + account.getUserName().split("@")[0]
									+ ((count != 0) ? count : "");
							flag = businessReposity.existsByBusinessId(businessId);
							count++;
						} while (flag);

						// save logo
						if (image != null) {
							String pathString = basisPath + "/image" + PathId.upload.getPath();

							byte[] data = Base64.getDecoder()
									.decode(image.split(",")[1].getBytes(StandardCharsets.UTF_8));
							OutputStream stream;

							String imageName = "/business_" + businessId.split("_")[1] + ".png";

							try {
								stream = new FileOutputStream(pathString + imageName);
								stream.write(data);
							} catch (IOException e) {
								e.printStackTrace();
							}

							String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null)
									.build().toUriString();

							business.setbusinessLogoPath(baseUrl + "/media/image/upload" + imageName);
						} else {
							String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null)
									.build().toUriString();
							business.setbusinessLogoPath(baseUrl + "/media/image/sample" + "/business.png");
						}

						// set link to business
						business.setbusinessId(businessId);
						businessReposity.save(business);
						account.setBusinessId(businessId);
						accountReposity.save(account);

						return account;
					} else {
						boolean check = businessReposity.existsByBusinessId(account.getBusinessId());
						if (!check) {
							String businessId = "";
							int count = 0;
							boolean flag = true;
							do {
								businessId = "BUSINESS_" + account.getUserName().split("@")[0]
										+ ((count != 0) ? count : "");
								flag = businessReposity.existsByBusinessId(businessId);
								count++;
							} while (flag);

							// save logo
							if (image != null) {
								String pathString = basisPath + "/image" + PathId.upload.getPath();

								byte[] data = Base64.getDecoder()
										.decode(image.split(",")[1].getBytes(StandardCharsets.UTF_8));
								OutputStream stream;

								String imageName = "/business_" + businessId.split("_")[1] + ".png";

								try {
									stream = new FileOutputStream(pathString + imageName);
									stream.write(data);
								} catch (IOException e) {
									e.printStackTrace();
								}

								String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null)
										.build().toUriString();

								business.setbusinessLogoPath(baseUrl + "/media/image/upload" + imageName);
							} else {
								String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null)
										.build().toUriString();
								business.setbusinessLogoPath(baseUrl + "/media/image/sample" + "/business.png");
							}

							business.setbusinessId(businessId);
							businessReposity.save(business);
							account.setBusinessId(businessId);
							accountReposity.save(account);

							return account;
						}
					}
				}
			}

		}
		return null;
	}

}
