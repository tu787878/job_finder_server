package de.tcg.jobFinder.reposity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.tcg.jobFinder.entity.BusinessCategory;

@Repository
public interface BusinessCategoryReposity extends JpaRepository<BusinessCategory, Long> {
	public BusinessCategory findByBusinessCategoryId(String businessCategoryId);
}
