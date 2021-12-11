package de.tcg.jobFinder.service;

import de.tcg.jobFinder.entity.AccountToken;

public interface AccountTokenService{
	
	public void saveAccountToken(AccountToken accountToken);
	public AccountToken getAccountTokenByAccessToken(String accessToken);
	
}
