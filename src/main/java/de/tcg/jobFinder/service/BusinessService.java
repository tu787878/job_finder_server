package de.tcg.jobFinder.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import de.tcg.jobFinder.entity.Business;
import de.tcg.jobFinder.entity.BusinessCategory;


public interface BusinessService {
	
	public ResponseEntity<?> getBusiness(HttpServletRequest request, String businessId);

	public ResponseEntity<?> getJobs(HttpServletRequest request, String businessId, boolean active);

	public ResponseEntity<?> getCategories(HttpServletRequest request, String businessId);

	public ResponseEntity<?> updateBusiness(HttpServletRequest request, Business business, String imageBase64);

	public Business getBusiness(String businessId);

	public BusinessCategory getCategoryById(String businessCategoryId);

}
