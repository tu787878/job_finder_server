package de.tcg.jobFinder.service;

import java.util.concurrent.ExecutionException;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import de.tcg.jobFinder.model.admin.AuthorizationAccount;

public interface EmployeeAccountService {

	public AuthorizationAccount loadUserByUsername(String username) throws UsernameNotFoundException, InterruptedException, ExecutionException;
}
