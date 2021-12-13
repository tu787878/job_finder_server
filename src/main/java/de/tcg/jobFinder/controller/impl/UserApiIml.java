package de.tcg.jobFinder.controller.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tcg.jobFinder.controller.UserApi;
import de.tcg.jobFinder.dto.SuccessResponse;
import de.tcg.jobFinder.dto.UserRequest;
import de.tcg.jobFinder.entity.User;
import de.tcg.jobFinder.service.JobService;
import de.tcg.jobFinder.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserApiIml implements UserApi {

	@Autowired
	UserService userService;

	@Autowired
	JobService jobService;

	@Override
	public ResponseEntity<?> getUsers(HttpServletRequest request) {

		List<User> users = new ArrayList<User>();
		users = userService.getUsers(request);

		if (users != null) {
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("users", users);
			return ResponseEntity.ok(new SuccessResponse(0, "success", res));
		}
		return ResponseEntity.ok(new SuccessResponse(1, "failed", null));
	}

	@Override
	public ResponseEntity<?> getUserById(HttpServletRequest request, String userId) {
		User user = null;

		user = userService.getUserById(request, userId);

		if (user != null) {
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("user", user);
			return ResponseEntity.ok(new SuccessResponse(0, "success", res));
		}
		return ResponseEntity.ok(new SuccessResponse(1, "failed", null));
	}

	@Override
	public ResponseEntity<?> updateUserById(HttpServletRequest request, String userId,
			@RequestBody(required = true) UserRequest userRequest) {
		User user = userRequestToUser(userRequest);

		boolean result = userService.updateUserById(request, user);

		if (result) {
			return ResponseEntity.ok(new SuccessResponse(0, "success", null));
		}
		return ResponseEntity.ok(new SuccessResponse(1, "failed", null));
	}

	public User userRequestToUser(UserRequest userRequest) {
		User user = new User();
		user.setAddress(userRequest.getAddress());
		user.setBirthday(userRequest.getBirthday());

//		TODO: convert json string to list 
		user.setBusinessCategories(null);

		user.setCity(jobService.getCityById(Long.valueOf(userRequest.getCityId())));
		user.setDescription(userRequest.getDescription());
		user.setFirstName(userRequest.getFirstName());
		user.setGender(Integer.valueOf(userRequest.getGender()));

//		TODO: convert json string to list 
		user.setJobCategories(null);

		user.setLastName(userRequest.getLastName());
		user.setPhone(Integer.valueOf(userRequest.getPhone()));
		user.setPostCode(userRequest.getPostCode());
		user.setUserId(userRequest.getUserId());

		return user;
	}

}
