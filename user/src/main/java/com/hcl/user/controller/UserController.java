package com.hcl.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.user.mapper.UserMapper;
import com.hcl.user.model.ResponseDTO;
import com.hcl.user.model.User;
import com.hcl.user.model.UserReponseDTO;
import com.hcl.user.model.UserRequestDTO;
import com.hcl.user.service.UserService;
import com.hcl.user.validation.UserValidations;

@RestController
@RequestMapping(value = "user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserValidations userValidations;
	
	@GetMapping(value = "/getUserByUserNameAndPassword/{userName}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> getUserById (@PathVariable("userName") String userName, @PathVariable("password") String password) {
		
		userValidations.validateUserLogIn(userName, password);
		User user = userService.getUserByUserNameAndPassword(userName, password);
        UserReponseDTO result = userMapper.convertEntityToResponseDTO(user);
        ResponseDTO response = new ResponseDTO(HttpStatus.OK.value(), Boolean.TRUE, result, null);
        return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> getAllUsers (Pageable pageRequest) {
		
		Page<User> userPage = userService.getAllUsers(pageRequest);
        Page<UserReponseDTO> result = userMapper.convertEntityPageToResponsePage(pageRequest, userPage);
        ResponseDTO response = new ResponseDTO(HttpStatus.OK.value(), Boolean.TRUE, result, null);
        return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/getUserById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> getUserById (@PathVariable("id") Long id) {
		
		User user = userService.getUserById(id);
        UserReponseDTO result = userMapper.convertEntityToResponseDTO(user);
        ResponseDTO response = new ResponseDTO(HttpStatus.OK.value(), Boolean.TRUE, result, null);
        return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "/createUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> createUser (@RequestBody UserRequestDTO userRequestDTO) {
		
		User user = userMapper.convertCreateRequestToEntity(userRequestDTO);
		userValidations.validateUserName(user.getUserName());
		user = userService.createUser(user);
		UserReponseDTO userReponseDTO = userMapper.convertEntityToResponseDTO(user);
		ResponseDTO response = new ResponseDTO(HttpStatus.OK.value(), Boolean.TRUE, userReponseDTO, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping(value = "/updateUser/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> updateUser (@PathVariable("id") Long id, @RequestBody UserRequestDTO userRequestDTO) {
		
		User user = userService.getUserById(id);
		userMapper.convertUpdateRequestToEntity(userRequestDTO, user);
		user = userService.updateUser(user);
		UserReponseDTO userReponseDTO = userMapper.convertEntityToResponseDTO(user);
		ResponseDTO response = new ResponseDTO(HttpStatus.OK.value(), Boolean.TRUE, userReponseDTO, null);
        return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "/deleteUser/{id}")
	public ResponseEntity<ResponseDTO> deleteUser (@PathVariable("id") Long id) {
		
		User user = userService.getUserById(id);
		userService.deleteUser(user);
		ResponseDTO response = new ResponseDTO(HttpStatus.OK.value(), Boolean.TRUE, null, null);
        return ResponseEntity.ok(response);
	}
	
}
