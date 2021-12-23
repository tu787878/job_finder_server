package de.tcg.jobFinder.reposity;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import de.tcg.jobFinder.entity.Job;
import de.tcg.jobFinder.entity.JobCategory;

@Repository
public interface JobReposity extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {

	List<Job> findByBusinessId(String businessId);

	List<Job> findByBusinessIdAndIsActive(String businessId, boolean active);

	@Query("SELECT q FROM Job q ORDER BY ?1 DESC")
	List<Job> findAllWithQuerySearch(String orderBy);

	Page<Job> findAll(Specification<Job> spec, Pageable pageable);

	List<Job> findAll(Specification<Job> spec);
	
	Page<Job> findByJobCategoryIn(Set<JobCategory> jobCategories, Pageable pageable);

	Job findByJobId(String jobId);

}
