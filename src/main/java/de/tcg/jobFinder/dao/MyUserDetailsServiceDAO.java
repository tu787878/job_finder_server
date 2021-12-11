package de.tcg.jobFinder.dao;

import java.util.concurrent.ExecutionException;

import org.springframework.security.core.userdetails.UserDetails;

import de.tcg.jobFinder.model.admin.AuthorizationAccount;

public interface MyUserDetailsServiceDAO {

	public AuthorizationAccount loadUserByUsername(String username) throws InterruptedException, ExecutionException;

}
