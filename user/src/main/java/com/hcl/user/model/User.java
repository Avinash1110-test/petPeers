package com.hcl.user.model;

import javax.persistence.*;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "user_name", name = "user_name_unique"))
@Getter
@Setter
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "user_name", length = 55)
	private String userName;
	
	@NotNull
	@Column(name = "user_password", length = 55)
	private String password;
	

}
