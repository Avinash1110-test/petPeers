package com.hcl.user.model;

import java.util.List;

import javax.persistence.Column;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserReponseDTO {
	
	private Long id;
	
	private String userName;
	
	private String userPassword;
	
	private List<PetResponseDTO> petResponseDTOs;
	

}
