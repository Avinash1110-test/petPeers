package com.hcl.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUserName (String userName);
	
	User findByUserNameAndPassword (String userName, String password);

}
