package com.hcl.user.service;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hcl.user.model.PetResponseDTO;

//userFeignClientService
@FeignClient(name = "pet")
@RibbonClient(name = "pet")
public interface FeignClientService {

	@GetMapping(value = "/getAllPets")
	public List<PetResponseDTO> getAllPets (Pageable pageRequest);
	
	@GetMapping(value = "/getPetById/{id}")
	public PetResponseDTO getPetById (@PathVariable("id") Long id);
	
	@GetMapping(value = "/getPetsByUserId/{userId}")
	public List<PetResponseDTO> getPetsByUserId (@PathVariable("userId") Long userId);
}
