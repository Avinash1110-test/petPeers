package com.hcl.user.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.user.exception.ErrorCodes;
import com.hcl.user.exception.ServiceException;
import com.hcl.user.repository.UserRepository;

@Service
public class UserValidations {
	
	@Autowired
	private UserRepository userRepository;
	
	public void validateUserName (String userName) {
		if (userRepository.findByUserName(userName) != null) {
			throw new ServiceException(ErrorCodes.USER_NAME_ALREADY_EXIST.errorKey);
		}
	}
	
	public void validateUserLogIn (String userName, String password) {
		if (userRepository.findByUserNameAndPassword(userName, password) == null) {
			throw new ServiceException(ErrorCodes.LOG_IN_VALIDATION.errorKey);
		}
	}

}
