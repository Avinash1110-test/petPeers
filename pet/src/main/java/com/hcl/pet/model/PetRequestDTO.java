package com.hcl.pet.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PetRequestDTO {

	private String petName;
	
	private Long petAge;
	
	private String petPlace;
}
