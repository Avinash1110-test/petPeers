package com.hcl.pet.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.pet.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
	
	List<Pet> findByPetOwnerId (Long userId);

}
