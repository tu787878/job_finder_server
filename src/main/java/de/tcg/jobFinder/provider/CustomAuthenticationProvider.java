package de.tcg.jobFinder.provider;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import de.tcg.jobFinder.exception.ApiRequestException;
import de.tcg.jobFinder.model.admin.AuthorizationAccount;
import de.tcg.jobFinder.service.MyUserDetailsService;

@Service
public class CustomAuthenticationProvider implements UserDetailsService {

	@Autowired
	MyUserDetailsService myUserDetailsService;

	@Override
	public UserDetails loadUserByUsername(String username) {
		AuthorizationAccount account;
		account = myUserDetailsService.loadUserByUsername(username);

		if (account == null || !account.getUsername().equalsIgnoreCase(username)) {
			throw new ApiRequestException("Incorrect username or password!");
		}

		Collection<? extends GrantedAuthority> authorities = account.getAuthorities();

		return new User(account.getUsername(), account.getPassword(), authorities);

	}

}
