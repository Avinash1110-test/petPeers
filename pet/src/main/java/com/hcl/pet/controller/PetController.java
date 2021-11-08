package com.hcl.pet.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.pet.mapper.PetMapper;
import com.hcl.pet.model.Pet;
import com.hcl.pet.model.PetRequestDTO;
import com.hcl.pet.model.PetResponseDTO;
import com.hcl.pet.model.ResponseDTO;
import com.hcl.pet.model.UserReponseDTO;
import com.hcl.pet.service.FeignClientService;
import com.hcl.pet.service.PetService;

@RestController
@RequestMapping(value = "pet")
public class PetController {

	@Autowired
	private PetService petService;
	
	@Autowired
	private PetMapper petMapper;
	
//	@Autowired
	private FeignClientService feignClientService;
	
	@GetMapping(value = "/getAllPets", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> getAllPets (Pageable pageRequest) {
		
		Page<Pet> pets = petService.getAllPets(pageRequest);
        Page<PetResponseDTO> result = petMapper.convertEntityPageToResponsePage(pageRequest, pets);
        ResponseDTO response = new ResponseDTO(HttpStatus.OK.value(), Boolean.TRUE, result, null);
        return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/getPetById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> getPetById (@PathVariable("id") Long id) {
		
		Pet pet = petService.getPetById(id);
        PetResponseDTO result = petMapper.convertEntityToResponseDTO(pet);
        ResponseDTO response = new ResponseDTO(HttpStatus.OK.value(), Boolean.TRUE, result, null);
        return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/getPetsByUserId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> getPetsByUserId (@PathVariable("userId") Long userId) {
		
		List<Pet> pets = petService.getPetsByUserId(userId);
		List<PetResponseDTO> result = new ArrayList<>();
		PetResponseDTO petResponseDTO = null;
		for (Pet pet : pets) {
			petResponseDTO = petMapper.convertEntityToResponseDTO(pet);
			result.add(petResponseDTO);
		}
        ResponseDTO response = new ResponseDTO(HttpStatus.OK.value(), Boolean.TRUE, result, null);
        return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "/createPet/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> createPet (@PathVariable("userId") Long userId, @RequestBody PetRequestDTO petRequestDTO) {
		
		UserReponseDTO userReponseDTO = feignClientService.getUserById(userId);
		PetResponseDTO petResponseDTO = null;
		if (userReponseDTO != null) {
			Pet pet = petMapper.convertCreateRequestToEntity(petRequestDTO);
			pet.setPetOwnerId(userId);
			pet = petService.createPet(pet);
			petResponseDTO = petMapper.convertEntityToResponseDTO(pet);
		}
		ResponseDTO response = new ResponseDTO(HttpStatus.OK.value(), Boolean.TRUE, petResponseDTO, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping(value = "/updatePet/{petId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> updatePet (@PathVariable("petId") Long petId, @RequestBody PetRequestDTO petRequestDTO) {
		
		Pet pet = petService.getPetById(petId);
		petMapper.convertUpdateRequestToEntity(petRequestDTO, pet);
		pet = petService.updatePet(pet);
		PetResponseDTO petResponseDTO = petMapper.convertEntityToResponseDTO(pet);
		ResponseDTO response = new ResponseDTO(HttpStatus.OK.value(), Boolean.TRUE, petResponseDTO, null);
        return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "/deletePet/{petId}")
	public ResponseEntity<ResponseDTO> deletePet (@PathVariable("petId") Long petId) {
		
		Pet pet = petService.getPetById(petId);
		petService.deletePet(pet);
		ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(), Boolean.TRUE, null, null);
		return ResponseEntity.ok(responseDTO);
	}
	
}
