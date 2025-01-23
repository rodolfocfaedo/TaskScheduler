package com.rodolfo.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodolfo.infrastructure.entity.Cellphone;

@Repository
public interface CellphoneRepository extends JpaRepository<Cellphone, Long>{
	
	

}
