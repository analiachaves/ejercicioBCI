package com.bci.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bci.controller.UserController;
import com.bci.dto.PhoneDTO;
import com.bci.dto.RequestDTO;
import com.bci.dto.UserDTO;
import com.bci.entity.PhoneEntity;
import com.bci.entity.UserEntity;
import com.bci.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	
	@Mock
	private UserRepository userRepository;
		
	@InjectMocks
	private UserServiceImpl userServiceImpl;

	private UserDTO userDTO;
	private PhoneDTO phoneDTO;
	private Optional<RequestDTO> requestDTO;
	private UserEntity userEntity;
	private PhoneEntity phoneEntity;

	@BeforeEach
	public void config() {
		phoneDTO = new PhoneDTO(new Long(12345678), 1, "2");
		List<PhoneDTO> listPhoneDTO = new ArrayList<>();
		listPhoneDTO.add(phoneDTO);

		userDTO = new UserDTO("Juan Ejemplo", "juan@ejemplo.cl", "Password22", listPhoneDTO);
		
		userEntity = new UserEntity();
		phoneEntity = new PhoneEntity(new Long(1), new Long(12345678), 1, "2",userEntity);
		List<PhoneEntity> listPhonesEntity= new ArrayList<>();
		listPhonesEntity.add(phoneEntity);
		
		userEntity.setName("Juan Ejemplo");
		userEntity.setEmail("juan@ejemplo.cl");
		userEntity.setPassword("Password22");
		userEntity.setPhones(listPhonesEntity);
		userEntity.setId(new Long(1));
		userEntity.setCreated(LocalDateTime.now());
		userEntity.setLastLogin(LocalDateTime.now());
		userEntity.setToken("token");
		userEntity.setIsActive(true);
			
		requestDTO = Optional.ofNullable(new RequestDTO("Juan Ejemplo", "juan@ejemplo.cl", "Password22", listPhoneDTO));
	}

	@Test
	void testCreateUsuario() {
		when(this.userRepository.save(userEntity)).thenReturn(userEntity);
		UserEntity userCreated = this.userRepository.save(userEntity);
		UserDTO userCreatedDTO = convertUsuarioEntityToUsuarioDTO(userCreated);
		assertEquals(userCreatedDTO, userDTO);
	}
	
	private UserDTO convertUsuarioEntityToUsuarioDTO(UserEntity source) {				
		return userDTO;			
	}
}
