package de.tcg.jobFinder.controller.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.tcg.jobFinder.controller.UserApi;
import de.tcg.jobFinder.dto.SuccessResponse;
import de.tcg.jobFinder.dto.UserRequest;
import de.tcg.jobFinder.entity.Account;
import de.tcg.jobFinder.entity.JobCategory;
import de.tcg.jobFinder.entity.User;
import de.tcg.jobFinder.service.AppliedJobService;
import de.tcg.jobFinder.service.JobService;
import de.tcg.jobFinder.service.UserService;
import de.tcg.jobFinder.service.impl.EmailServiceImpl;
import de.tcg.jobFinder.template.email.TemplateName;

@RestController
@RequestMapping("/api/user")
public class UserApiIml implements UserApi {

	@Autowired
	UserService userService;

	@Autowired
	JobService jobService;

	@Autowired
	AppliedJobService appliedJobService;
	
	@Autowired
	EmailServiceImpl emailServiceImpl;

	/*
	 * all user
	 * 
	 */
	@Override
	public ResponseEntity<?> getUsers() {

		List<User> users = new ArrayList<User>();
		users = userService.getUsers();

		if (users != null) {
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("users", users);
			return ResponseEntity.ok(new SuccessResponse(0, "success", res));
		}
		return ResponseEntity.ok(new SuccessResponse(1, "failed", null));
	}

	/*
	 * =============MANAGER USER==================================
	 * ===========================================================
	 */
	@Override
	public ResponseEntity<?> getUserById(String userId) {
		User user = null;

		user = userService.getUserById(userId);

		if (user != null) {
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("user", user);
			return ResponseEntity.ok(new SuccessResponse(0, "success", res));
		}
		return ResponseEntity.ok(new SuccessResponse(1, "failed", null));
	}
	
	@Override
	public ResponseEntity<?> createUser(HttpServletRequest request,
			@RequestBody(required = false) UserRequest userRequest) {
		User user = userRequestToUser(userRequest);
		Account account = userService.createUser(request, user, userRequest.getAvatar());
		// System.out.println("ok");
		if (account != null) {
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("account", account);
			return ResponseEntity.ok(new SuccessResponse(0, "success", res));
		}
		return ResponseEntity.ok(new SuccessResponse(0, "fail", null));
	}

	@Override
	public ResponseEntity<?> updateUser(HttpServletRequest request,
			@RequestBody(required = false) UserRequest userRequest, @PathVariable(name = "userId") String userId) {
		User user = userRequestToUser(userRequest);

		boolean result = userService.updateUserById(request, user, userRequest.getAvatar());

		if (result) {
			return ResponseEntity.ok(new SuccessResponse(0, "success", null));
		}
		return ResponseEntity.ok(new SuccessResponse(1, "failed", null));
	}
	

	@Override
	public ResponseEntity<?> deleteUser(HttpServletRequest request, String userId) {
		boolean check = userService.deleteUser(request, userId);
		if (check) {
			return ResponseEntity.ok(new SuccessResponse(0, "success", null));
		}
		return ResponseEntity.ok(new SuccessResponse(1, "failed", null));
	}

	/*
	 * =============MANAGER APPLIED JOB==================================
	 * ===========================================================
	 */
	@Override
	public ResponseEntity<?> getAppliedJob(HttpServletRequest request,
			@RequestParam(required = false) Map<String, String> restDTO, @PathVariable(name = "userId") String userId) {
		int count = 0, page = 0;

		if (restDTO.get("count") != null && restDTO.get("page") != null) {
			count = Integer.valueOf(restDTO.get("count"));
			page = Integer.valueOf(restDTO.get("page"));
		}

		Map<String, Object> jobs = appliedJobService.findAppliedJob(request, userId, count, page);

		Map<String, Object> res = new HashMap<String, Object>();
		res.put("jobs", jobs.get("jobs"));

		if (count != 0) {
			res.put("totalPages", jobs.get("totalPages"));
			res.put("currentPage", jobs.get("currentPage"));
			res.put("totalCount", jobs.get("totalCount"));
			res.put("countPerPage", count);
		}

		return ResponseEntity.ok(new SuccessResponse(0, "success", res));
	}


	@Override
	public ResponseEntity<?> applyJob(HttpServletRequest request, Map<String, String> restDTO, String userId,
			String jobId) throws MessagingException {
		boolean result = appliedJobService.applyJob(request, userId, jobId);
		if (result) {
			String userMail = userService.getEmailUserById(userId);
			//emailServiceImpl.sendMail(null, TemplateName.APPLIEDJOBTOBUSINESS, "Bạn có một yêu cầu mới!", user.);
			return ResponseEntity.ok(new SuccessResponse(0, "success", null));
		}
			
		return ResponseEntity.ok(new SuccessResponse(1, "faild", null));
	}

	@Override
	public ResponseEntity<?> deleteAppliedJob(HttpServletRequest request, String userId, String jobId) {
		boolean result = appliedJobService.disApplyJob(request, userId, jobId);
		if (result)
			return ResponseEntity.ok(new SuccessResponse(0, "success", null));
		return ResponseEntity.ok(new SuccessResponse(1, "faild", null));
	}

	/*
	 * =============RELATE JOB==================================
	 * ===========================================================
	 */
	@Override
	public ResponseEntity<?> getRelateJobs(HttpServletRequest request, String userId, Map<String, String> restDTO) {
		int count = 0, page = 0;

		if (restDTO.get("count") != null && restDTO.get("page") != null) {
			count = Integer.valueOf(restDTO.get("count"));
			page = Integer.valueOf(restDTO.get("page"));
		}

		Map<String, Object> jobs = jobService.findRelateJob(request, userId, count, page);

		Map<String, Object> res = new HashMap<String, Object>();
		res.put("jobs", jobs.get("jobs"));

		if (count != 0) {
			res.put("totalPages", jobs.get("totalPages"));
			res.put("currentPage", jobs.get("currentPage"));
			res.put("totalCount", jobs.get("totalCount"));
			res.put("countPerPage", count);
		}

		return ResponseEntity.ok(new SuccessResponse(0, "success", res));
	}

	/*
	 * HELPER METHOD
	 */
	public User userRequestToUser(UserRequest userRequest) {
		User user = new User();
		user.setAddress(userRequest.getAddress());
		user.setBirthday(userRequest.getBirthday());

		user.setCity(jobService.getCityById(Long.valueOf(userRequest.getCityId())));
		user.setDescription(userRequest.getDescription());
		user.setFirstName(userRequest.getFirstName());
		user.setGender(Integer.valueOf(userRequest.getGender()));

		List<JobCategory> jobCategories = new ArrayList<JobCategory>();
		List<String> myList = new ArrayList<String>(Arrays.asList(userRequest.getJobCategoryIds().split(",")));
		for (String l : myList) {
			JobCategory jobCategory = jobService.getJobCategoryById(Long.valueOf(l));
			jobCategories.add(jobCategory);
		}
		user.setJobCategories(Set.copyOf(jobCategories));

		user.setLastName(userRequest.getLastName());
		user.setPhone(Integer.valueOf(userRequest.getPhone()));
		user.setPostCode(userRequest.getPostCode());
		user.setUserId(userRequest.getUserId());

		return user;
	}
}
