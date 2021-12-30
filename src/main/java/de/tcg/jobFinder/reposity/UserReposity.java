package de.tcg.jobFinder.reposity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.tcg.jobFinder.entity.User;

@Repository
public interface UserReposity extends JpaRepository<User, Long> {

	User findByUserId(String userId);

	boolean existsByUserId(String userId);

}
