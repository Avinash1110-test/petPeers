package com.hcl.pet.service;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hcl.pet.model.UserReponseDTO;

//petFeignClientService
@FeignClient(name = "user")
@RibbonClient(name = "user")
public interface FeignClientService {

	@GetMapping(value = "/getAllUsers")
	public List<UserReponseDTO> getAllUsers (Pageable pageRequest);
	
	@GetMapping(value = "/getUserById/{id}")
	public UserReponseDTO getUserById (@PathVariable("id") Long id);
}
