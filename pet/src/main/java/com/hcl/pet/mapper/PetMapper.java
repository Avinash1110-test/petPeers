package com.hcl.pet.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.hcl.pet.model.Pet;
import com.hcl.pet.model.PetRequestDTO;
import com.hcl.pet.model.PetResponseDTO;
import com.hcl.pet.model.UserReponseDTO;
import com.hcl.pet.model.PetResponseDTO.UserDTO;
import com.hcl.pet.service.FeignClientService;

@Component
public class PetMapper {

	@Autowired
	private ModelMapper modelMapper;
	
//	@Autowired
	private FeignClientService feignClientService;
	
	public PetResponseDTO convertEntityToResponseDTO(Pet pet) {
		PetResponseDTO petResponseDTO = modelMapper.map(pet, PetResponseDTO.class);
        petResponseDTO.setId(petResponseDTO.getId());
        UserReponseDTO userReponseDTO = feignClientService.getUserById(pet.getPetOwnerId());
        if (userReponseDTO != null) {
			petResponseDTO.setUser(new UserDTO(userReponseDTO.getId(),userReponseDTO.getUserName()));
		}
        return petResponseDTO;
    }

    public Pet convertCreateRequestToEntity(PetRequestDTO petRequestDTO) {
        return modelMapper.map(petRequestDTO, Pet.class);
    }

    public void convertUpdateRequestToEntity(PetRequestDTO petRequestDTO, Pet pet) {
        modelMapper.map(petRequestDTO, pet);
    }

    public Page<PetResponseDTO> convertEntityPageToResponsePage(Pageable pageRequest, Page<Pet> pets) {
        List<PetResponseDTO> dtos = new ArrayList<>();
        pets.forEach(e -> dtos.add(convertEntityToResponseDTO(e)));
        return new PageImpl<>(dtos, pageRequest, pets.getTotalElements());
    }
}
