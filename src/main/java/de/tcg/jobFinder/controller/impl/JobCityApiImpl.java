package de.tcg.jobFinder.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tcg.jobFinder.controller.JobCityApi;
import de.tcg.jobFinder.dto.SuccessResponse;
import de.tcg.jobFinder.entity.City;
import de.tcg.jobFinder.service.JobService;

@RestController
@RequestMapping("/api/jobCity")
public class JobCityApiImpl implements JobCityApi {

	@Autowired
	JobService jobService;

	@Override
	public ResponseEntity<?> getJobCities() {
		List<City> cities = null;
		cities = jobService.getJobCities();

		if (cities != null) {
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("cities", cities);
			return ResponseEntity.ok(new SuccessResponse(0, "success", res));
		}

		return ResponseEntity.ok(new SuccessResponse(1, "failed", null));
	}

}
