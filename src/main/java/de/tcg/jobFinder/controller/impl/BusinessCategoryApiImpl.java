package de.tcg.jobFinder.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tcg.jobFinder.controller.BusinessCategoryApi;
import de.tcg.jobFinder.dto.SuccessResponse;
import de.tcg.jobFinder.entity.BusinessCategory;
import de.tcg.jobFinder.service.BusinessService;

@RestController
@RequestMapping("/api/businessCategory")
public class BusinessCategoryApiImpl implements BusinessCategoryApi{

	@Autowired
	BusinessService businessService;
	
	@Override
	public ResponseEntity<?> getBusiness(HttpServletRequest request) {
		List<BusinessCategory> businessCategories = businessService.getCategories(request);
		if (businessCategories != null) {
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("businessCategories", businessCategories);
			return ResponseEntity.ok(new SuccessResponse(0, "success", res));
		}
		return ResponseEntity.ok(new SuccessResponse(1, "fail", null));
	}

}
