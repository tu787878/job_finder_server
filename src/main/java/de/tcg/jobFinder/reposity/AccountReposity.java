package de.tcg.jobFinder.reposity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.tcg.jobFinder.entity.Account;

@Repository
public interface AccountReposity extends JpaRepository<Account, Long> {
	public Account findByUserName(String username);

	public Account findByAccountId(String acccountId);

	public boolean existsByUserName(String userName);

	public boolean existsByAccountId(String accountId);
}
