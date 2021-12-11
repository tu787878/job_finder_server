package de.tcg.jobFinder.reposity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.tcg.jobFinder.entity.JobCategory;

@Repository
public interface JobCategoryReposity extends JpaRepository<JobCategory, Long>{
	public JobCategory findById(long id);

}
