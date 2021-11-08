package com.hcl.pet.model;

import javax.persistence.*;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "pet_name", length = 55)
	private String petName;
	
	@Column(name = "pet_age", length = 2)
	private Long petAge;
	
	@Column(name = "pet_place", length = 55)
	private String petPlace;
	
	@NotNull
	@Column(name = "pet_owner_id")
	private Long petOwnerId;

}
