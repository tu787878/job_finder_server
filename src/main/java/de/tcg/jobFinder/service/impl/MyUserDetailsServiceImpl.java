package de.tcg.jobFinder.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tcg.jobFinder.entity.Account;
import de.tcg.jobFinder.model.admin.AuthorizationAccount;
import de.tcg.jobFinder.model.admin.Role;
import de.tcg.jobFinder.reposity.AccountReposity;
import de.tcg.jobFinder.service.MyUserDetailsService;

@Service
public class MyUserDetailsServiceImpl extends UntilService implements MyUserDetailsService {

	@Autowired
	private AccountReposity accountReposity;

	@Override
	public AuthorizationAccount loadUserByUsername(String username) {

		Account account = accountReposity.findByUserName(username);

		if (account == null)
			return null;
		
		AuthorizationAccount authorizationAccount = new AuthorizationAccount(account.getUserName(),
				account.getPassword(), account.getAccountId(), new ArrayList<Role>(), new ArrayList<String>());
		authorizationAccount.getAuthorities().add(new Role("user"));

		return authorizationAccount;
	}

	@Override
	public Account getAccountByAccountId(String accountId) {
		return accountReposity.findByAccountId(accountId);
	}

	@Override
	public Account createAccount(Account account) {
		boolean check = accountReposity.existsByUserName(account.getUserName());
		if(!check) {
			String accountId = "";
			boolean flag = true;
			int count = 0;
			do {
				accountId = account.getUserName().split("@")[0] + ((count != 0) ? count : "");
				flag = accountReposity.existsByAccountId(accountId);
				count++;
			}while(flag);
			
			account.setAccountId(accountId);
			
			Account newAccount = accountReposity.save(account);
			return newAccount;
		}
		return null;
	}

}
