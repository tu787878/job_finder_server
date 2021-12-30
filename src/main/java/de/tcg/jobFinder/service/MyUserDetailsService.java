package de.tcg.jobFinder.service;

import de.tcg.jobFinder.entity.Account;
import de.tcg.jobFinder.model.admin.AuthorizationAccount;

public interface MyUserDetailsService{

	public AuthorizationAccount loadUserByUsername(String username);

	public Account getAccountByAccountId(String accountId);

	public Account createAccount(Account account);

}
