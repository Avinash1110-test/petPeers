package com.hcl.pet.service;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hcl.pet.exception.ErrorCodes;
import com.hcl.pet.exception.ServiceException;
import com.hcl.pet.model.Pet;
import com.hcl.pet.repository.PetRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@Transactional
public class PetServiceImpl implements PetService {
	
	private PetRepository petRepository;

	public PetServiceImpl(PetRepository petRepository) {
		this.petRepository = petRepository;
	}

	@Override
	public Page<Pet> getAllPets(Pageable pageRequest) {
		
		return petRepository.findAll(pageRequest);
	}

	@Override
	public Pet getPetById(Long id) {
		
		return petRepository.findById(id).orElseThrow(() -> new ServiceException(ErrorCodes.PET_ID_DOES_NOT_EXIST.errorKey));
	}
	
	@Override
	public List<Pet> getPetsByUserId(Long userId) {
		
		return petRepository.findByPetOwnerId(userId);
	}

	@Override
	public Pet createPet(Pet pet) {
		
		return petRepository.save(pet);
	}

	@Override
	public Pet updatePet(Pet pet) {
		
		return petRepository.save(pet);
	}

	@Override
	public void deletePet(Pet pet) {
		
		petRepository.delete(pet);

	}

}
