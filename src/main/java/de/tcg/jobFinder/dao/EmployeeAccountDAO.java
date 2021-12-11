package de.tcg.jobFinder.dao;

import de.tcg.jobFinder.model.admin.AuthorizationAccount;

public interface EmployeeAccountDAO {
	public AuthorizationAccount loadUserByUsername(final String username);

}
