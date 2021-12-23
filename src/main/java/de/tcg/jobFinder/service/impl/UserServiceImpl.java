package de.tcg.jobFinder.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import de.tcg.jobFinder.entity.Account;
import de.tcg.jobFinder.entity.AccountToken;
import de.tcg.jobFinder.entity.AppliedJob;
import de.tcg.jobFinder.entity.Job;
import de.tcg.jobFinder.entity.JobCategory;
import de.tcg.jobFinder.entity.User;
import de.tcg.jobFinder.reposity.AppliedJobReposity;
import de.tcg.jobFinder.reposity.UserReposity;
import de.tcg.jobFinder.service.AccountTokenService;
import de.tcg.jobFinder.service.MyUserDetailsService;
import de.tcg.jobFinder.service.UserService;

@Service
public class UserServiceImpl extends UntilService implements UserService {

	@Autowired
	UserReposity userReposity;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private AccountTokenService accountTokenService;
	
	@Autowired
	private AppliedJobReposity appliedJobReposity;

	@Override
	public List<User> getUsers(HttpServletRequest request) {

		return userReposity.findAll();
	}

	@Override
	public User getUserById(HttpServletRequest request, String userId) {
		String token = toToken(request);
		if (token != null) {
			AccountToken accountToken = accountTokenService.getAccountTokenByAccessToken(token);
			if (accountToken != null && accountToken.isActive()) {
				User user = userReposity.findByUserId(userId);
				return user;
			}
		}

		return null;
	}

	@Override
	public boolean updateUserById(HttpServletRequest request, User user) {
		String token = toToken(request);
		if (token != null) {
			AccountToken accountToken = accountTokenService.getAccountTokenByAccessToken(token);
			if (accountToken != null && accountToken.isActive()) {
				Account account = myUserDetailsService.getAccountByAccountId(accountToken.getAccountId());
				String userId = account.getUserId();
				if (!account.isBusiness() && userId.equals(user.getUserId())) {
					userReposity.save(user);
					return true;
				}
			}

		}
		return false;

	}


}
