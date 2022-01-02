package de.tcg.jobFinder.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import de.tcg.jobFinder.entity.Account;
import de.tcg.jobFinder.entity.Business;
import de.tcg.jobFinder.entity.BusinessCategory;


public interface BusinessService {
	
	public List<BusinessCategory> getCategories(HttpServletRequest request);

	public ResponseEntity<?> getJobs(HttpServletRequest request, String businessId, boolean active);

	public boolean updateBusiness(HttpServletRequest request, Business business, String imageBase64);

	public Business getBusiness(String businessId);

	public BusinessCategory getCategoryById(String businessCategoryId);

	public Account createBusiness(HttpServletRequest request, Business business, String image);

	public boolean deleteBusiness(HttpServletRequest request, String businessId);

}
