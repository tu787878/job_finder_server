package de.tcg.jobFinder.reposity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.tcg.jobFinder.entity.Business;

@Repository
public interface BusinessReposity extends JpaRepository<Business, Long> {
	public Business findByBusinessId(String businessId);

	public boolean existsByBusinessId(String businessId);
	
}
