package com.hcl.pet.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

	private String userName;
	
	private String userPassword;
	
	private List<PetRequestDTO> petRequestDTOs;
}
