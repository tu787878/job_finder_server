package de.tcg.jobFinder.reposity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.tcg.jobFinder.entity.AccountToken;

@Repository
public interface AccountTokenReposity extends JpaRepository<AccountToken, Long>{
	public AccountToken findByAccessToken(String accessToken);
	
}
