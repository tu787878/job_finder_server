package de.tcg.jobFinder.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tcg.jobFinder.entity.Account;
import de.tcg.jobFinder.model.admin.AuthorizationAccount;
import de.tcg.jobFinder.model.admin.Role;
import de.tcg.jobFinder.reposity.AccountReposity;
import de.tcg.jobFinder.service.EmployeeAccountService;

@Service
public class EmployeeAccountServiceImpl implements EmployeeAccountService {

	@Autowired
	private AccountReposity accountReposity;

	@Override
	public AuthorizationAccount loadUserByUsername(String username) {

		Account account = accountReposity.findByUserName(username);

		if (account == null)
			return null;
		
		System.out.println(account);
		AuthorizationAccount authorizationAccount = new AuthorizationAccount(account.getUserName(),
				account.getPassword(), account.getAccountId(), new ArrayList<Role>(), new ArrayList<String>());
		authorizationAccount.getAuthorities().add(new Role("user"));

		return authorizationAccount;
	}

}
