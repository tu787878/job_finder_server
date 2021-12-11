package de.tcg.jobFinder.reposity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.tcg.jobFinder.entity.Destination;

@Repository
public interface DestinationReposity extends JpaRepository<Destination, Long>{
	public Page<Destination> findAll(Pageable pageable);
}
