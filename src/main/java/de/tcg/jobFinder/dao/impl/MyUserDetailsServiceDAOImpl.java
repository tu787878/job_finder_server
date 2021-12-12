package de.tcg.jobFinder.dao.impl;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.tcg.jobFinder.dao.MyUserDetailsServiceDAO;
import de.tcg.jobFinder.model.admin.AuthorizationAccount;
import de.tcg.jobFinder.provider.FirebaseInitializer;

@Repository
public class MyUserDetailsServiceDAOImpl implements MyUserDetailsServiceDAO {

	@Autowired
	FirebaseInitializer db;

	@Override
	public AuthorizationAccount loadUserByUsername(String username) throws InterruptedException, ExecutionException {
		
		return null;
	}

}
