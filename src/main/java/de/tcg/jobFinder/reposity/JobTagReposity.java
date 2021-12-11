package de.tcg.jobFinder.reposity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.tcg.jobFinder.entity.JobTag;

@Repository
public interface JobTagReposity extends JpaRepository<JobTag, Long>{

	public JobTag findById(long jobTagId);

}
