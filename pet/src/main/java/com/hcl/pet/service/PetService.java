package com.hcl.pet.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hcl.pet.model.Pet;

public interface PetService {

	public abstract Page<Pet> getAllPets(Pageable pageRequest);
	
	public abstract Pet getPetById(Long id);
	
	public abstract List<Pet> getPetsByUserId(Long userId);
	
	public abstract Pet createPet(Pet pet);
	
	public abstract Pet updatePet(Pet pet);
	
	public abstract void deletePet(Pet pet);
}
