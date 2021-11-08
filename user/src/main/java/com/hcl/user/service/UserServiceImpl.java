package com.hcl.user.service;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hcl.user.exception.ErrorCodes;
import com.hcl.user.exception.ServiceException;
import com.hcl.user.model.User;
import com.hcl.user.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@Transactional
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public Page<User> getAllUsers(Pageable pageRequest) {
		
		return userRepository.findAll(pageRequest);
	}

	@Override
	public User getUserById(Long id) {
		
		return userRepository.findById(id).orElseThrow(() -> new ServiceException(ErrorCodes.USER_ID_DOES_NOT_EXIST.errorKey));
	}
	
	@Override
	public User getUserByUserNameAndPassword(String userName, String password) {
		
		return userRepository.findByUserNameAndPassword(userName, password);
	}

	@Override
	public User createUser(User user) {
		
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(User user) {
		
		userRepository.delete(user);

	}

}
