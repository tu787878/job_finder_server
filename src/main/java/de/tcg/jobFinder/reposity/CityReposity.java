package de.tcg.jobFinder.reposity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.tcg.jobFinder.entity.City;

@Repository
public interface CityReposity extends JpaRepository<City, Long>{
	public City findById(long id);

}
