package de.tcg.jobFinder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tcg.jobFinder.entity.AccountToken;
import de.tcg.jobFinder.reposity.AccountTokenReposity;
import de.tcg.jobFinder.service.AccountTokenService;

@Service
public class AccountTokenServiceImpl implements AccountTokenService {

	@Autowired
	private AccountTokenReposity accountTokenReposity;

	@Override
	public void saveAccountToken(AccountToken accountToken) {
		accountTokenReposity.save(accountToken);

	}

	@Override
	public AccountToken getAccountTokenByAccessToken(String accessToken) {
		return accountTokenReposity.findByAccessToken(accessToken);
	}

}
