package com.rodolfo.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rodolfo.infrastructure.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	
	boolean existsByEmail(String email);
	
	Optional<Person> findByEmail(String email);
	
	@Transactional
	void deleteByEmail(String email);

}
