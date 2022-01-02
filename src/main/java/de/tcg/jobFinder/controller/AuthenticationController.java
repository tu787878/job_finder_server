package de.tcg.jobFinder.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.tcg.jobFinder.dto.AccountRequest;
import de.tcg.jobFinder.dto.AuthenticationRequest;
import de.tcg.jobFinder.dto.SuccessResponse;
import de.tcg.jobFinder.entity.Account;
import de.tcg.jobFinder.entity.AccountToken;
import de.tcg.jobFinder.exception.ApiRequestException;
import de.tcg.jobFinder.model.admin.AuthorizationAccount;
import de.tcg.jobFinder.service.AccountTokenService;
import de.tcg.jobFinder.service.MyUserDetailsService;
import de.tcg.jobFinder.util.JwtUtil;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private AccountTokenService accountTokenService;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> creatAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new ApiRequestException("Incorrect username or password!");
		}
		final AuthorizationAccount userDetails = myUserDetailsService
				.loadUserByUsername(authenticationRequest.getUserName());
		final String jwt = jwtTokenUtil.generateToken(userDetails);

		AccountToken accountToken = new AccountToken(userDetails.getAccountId(), jwt, true);
		accountTokenService.saveAccountToken(accountToken);

		Map<String, String> res = new HashMap<String, String>();
		res.put("token", jwt);
		return ResponseEntity.ok(new SuccessResponse(0, "success", res));
	}
	
	@RequestMapping(value = "/authenticate/new", method = RequestMethod.POST)
	public ResponseEntity<?> createAccount(@RequestBody AccountRequest accountRequest){
		Account account = new Account();
		account.setActive(false);
		account.setUserId("");
		account.setBusinessId("");
		account.setBusiness(accountRequest.isBusiness());
		account.setUserName(accountRequest.getUserName());
		account.setPassword(accountRequest.getPassword());
		
		Account newAccount = myUserDetailsService.createAccount(account);
		
		if(newAccount != null) {
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("account", newAccount);
			
			final AuthorizationAccount userDetails = myUserDetailsService
					.loadUserByUsername(newAccount.getUserName());
			final String jwt = jwtTokenUtil.generateToken(userDetails);

			AccountToken accountToken = new AccountToken(userDetails.getAccountId(), jwt, true);
			accountTokenService.saveAccountToken(accountToken);
			
			res.put("token", accountToken.getAccessToken());
			return ResponseEntity.ok(new SuccessResponse(0, "success", res));
		}
		
		return ResponseEntity.ok(new SuccessResponse(0, "fail", null));
	}

	@RequestMapping(value = "/authenticate/:token", method = RequestMethod.GET)
	public ResponseEntity<?> activeEmail(@PathVariable(name = "token") String token) {

		return ResponseEntity.ok(new SuccessResponse(0, "success", null));
	}

	@RequestMapping(value = "/authenticate/helloWorld", method = RequestMethod.GET)
	public ResponseEntity<?> helloWorld(@RequestBody AuthenticationRequest authenticationRequest) {

		return ResponseEntity.ok(new SuccessResponse(0, "success", null));
	}

	@RequestMapping(value = "/authenticate/checkToken", method = RequestMethod.GET)
	public ResponseEntity<?> checkToken(HttpServletRequest request) {
		final String authorizationHeader = request.getHeader("Authorization");
		@SuppressWarnings("unused")
		String username = null;
		String jwt = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			username = jwtTokenUtil.extractUsername(jwt);
		}

		if (jwt != null) {
			AccountToken accountToken = accountTokenService.getAccountTokenByAccessToken(jwt);
			if (accountToken != null && accountToken.isActive()) {
				Map<String, Object> res = new HashMap<String, Object>();
				Account account = myUserDetailsService.getAccountByAccountId(accountToken.getAccountId());
				res.put("account", account);
				return ResponseEntity.ok(new SuccessResponse(0, "success", res));
			}
		}

		return ResponseEntity.ok(new SuccessResponse(1, "failed", null));
	}

}
