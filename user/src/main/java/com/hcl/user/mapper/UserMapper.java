package com.hcl.user.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.hcl.user.model.PetResponseDTO;
import com.hcl.user.model.User;
import com.hcl.user.model.UserReponseDTO;
import com.hcl.user.model.UserRequestDTO;
import com.hcl.user.service.FeignClientService;

@Component
public class UserMapper {

	@Autowired
	private ModelMapper modelMapper;
	
//	@Autowired
	private FeignClientService feignClientService;
	
	public UserReponseDTO convertEntityToResponseDTO(User user) {
		UserReponseDTO userReponseDTO = modelMapper.map(user, UserReponseDTO.class);
        userReponseDTO.setId(userReponseDTO.getId());
        List<PetResponseDTO> petResponseDTOs = feignClientService.getPetsByUserId(userReponseDTO.getId());
        if (!petResponseDTOs.isEmpty()) {
        	userReponseDTO.setPetResponseDTOs(petResponseDTOs);
		}
        return userReponseDTO;
    }

    public User convertCreateRequestToEntity(UserRequestDTO userRequestDTO) {
        return modelMapper.map(userRequestDTO, User.class);
    }

    public void convertUpdateRequestToEntity(UserRequestDTO userRequestDTO, User user) {
        modelMapper.map(userRequestDTO, user);
    }

    public Page<UserReponseDTO> convertEntityPageToResponsePage(Pageable pageRequest, Page<User> users) {
        List<UserReponseDTO> dtos = new ArrayList<>();
        users.forEach(e -> dtos.add(convertEntityToResponseDTO(e)));
        return new PageImpl<>(dtos, pageRequest, users.getTotalElements());
    }
}
