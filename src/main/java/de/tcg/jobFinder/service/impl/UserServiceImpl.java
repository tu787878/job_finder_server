package de.tcg.jobFinder.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import de.tcg.jobFinder.entity.Account;
import de.tcg.jobFinder.entity.AccountToken;
import de.tcg.jobFinder.entity.User;
import de.tcg.jobFinder.reposity.UserReposity;
import de.tcg.jobFinder.service.AccountTokenService;
import de.tcg.jobFinder.service.MyUserDetailsService;
import de.tcg.jobFinder.service.UserService;

public class UserServiceImpl extends UntilService implements UserService {

	@Autowired
	UserReposity userReposity;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private AccountTokenService accountTokenService;

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
