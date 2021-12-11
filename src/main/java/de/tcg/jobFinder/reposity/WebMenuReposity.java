package de.tcg.jobFinder.reposity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.tcg.jobFinder.entity.WebMenu;

@Repository
public interface WebMenuReposity extends JpaRepository<WebMenu, Long>{
	List<WebMenu> findByVisible(boolean isVisible);
}
