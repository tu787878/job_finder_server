package de.tcg.jobFinder.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.tcg.jobFinder.dao.EmployeeAccountDAO;
import de.tcg.jobFinder.model.admin.AuthorizationAccount;
import de.tcg.jobFinder.provider.FirebaseInitializer;

@Repository
public class EmployeeAccountDAOImpl implements EmployeeAccountDAO {
	@Autowired
	FirebaseInitializer db;

	@Override
	public AuthorizationAccount loadUserByUsername(String username) {
		return null;
	}

}
