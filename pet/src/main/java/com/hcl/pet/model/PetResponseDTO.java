package com.hcl.pet.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PetResponseDTO {
	
	private Long id;
	
	private String petName;
	
	private Long petAge;
	
	private String petPlace;
	
	private UserDTO user;
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class UserDTO {
		
		private Long id;
		private String userName;
	}


}
