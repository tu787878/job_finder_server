package de.tcg.jobFinder.reposity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import de.tcg.jobFinder.entity.Job;

@Repository
public interface JobReposity extends JpaRepository<Job, Long> {

	List<Job> findByBusinessId(String businessId);

	List<Job> findByBusinessIdAndIsActive(String businessId, boolean active);

	@Query("SELECT q FROM Job q ORDER BY ?1 DESC")
	List<Job> findAllWithQuerySearch(String orderBy);

}
