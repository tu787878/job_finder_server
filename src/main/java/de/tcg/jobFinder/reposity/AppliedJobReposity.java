package de.tcg.jobFinder.reposity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.tcg.jobFinder.entity.AppliedJob;
import de.tcg.jobFinder.entity.User;

@Repository
public interface AppliedJobReposity extends JpaRepository<AppliedJob, Long>{

	long countByJobId(long id);

	boolean existsByUserIdAndJobId(User userId, long id);

	AppliedJob findByUserIdAndJobId(User userId, long jobId);

	Page<AppliedJob> findByUserId(User userId, Pageable page);

	Page<AppliedJob> findByBusinessId(String userId, Pageable page);

}
