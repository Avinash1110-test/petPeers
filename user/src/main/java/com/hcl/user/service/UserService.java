package com.hcl.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hcl.user.model.User;

public interface UserService {
	
	public abstract Page<User> getAllUsers(Pageable pageRequest);
	
	public abstract User getUserById(Long id);
	
	public abstract User getUserByUserNameAndPassword(String userName, String password);
	
	public abstract User createUser(User user);
	
	public abstract User updateUser(User user);
	
	public abstract void deleteUser(User user);

}
