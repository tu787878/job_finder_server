package de.tcg.jobFinder.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tcg.jobFinder.controller.UserApi;
import de.tcg.jobFinder.dto.SuccessResponse;
import de.tcg.jobFinder.entity.Job;

@RestController
@RequestMapping("/api/user")
public class UserApiIml implements UserApi {

	@Autowired
	de.tcg.jobFinder.service.JobService jobService;


}
