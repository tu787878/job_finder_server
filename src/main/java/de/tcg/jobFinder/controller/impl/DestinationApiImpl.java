package de.tcg.jobFinder.controller.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tcg.jobFinder.controller.DestinationApi;
import de.tcg.jobFinder.dto.SuccessResponse;
import de.tcg.jobFinder.reposity.DestinationReposity;

@RestController
@RequestMapping("/api/destinations")
public class DestinationApiImpl implements DestinationApi {
	@Autowired
	DestinationReposity destinationReposity;

	@Override
	public ResponseEntity<?> getAll(String position) {
		Map<String, Object> data = new HashMap<String, Object>();
		
		
		return ResponseEntity.ok(new SuccessResponse(0, "success", data));
	}
	
	

}
