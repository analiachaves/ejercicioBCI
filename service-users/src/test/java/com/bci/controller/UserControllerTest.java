package com.bci.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bci.dto.PhoneDTO;
import com.bci.dto.RequestDTO;
import com.bci.dto.UserDTO;
import com.bci.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@Mock
	private UserServiceImpl userService;

	@InjectMocks
	private UserController userController;

	private Optional<UserDTO> userDTO;
	private PhoneDTO phoneDTO;
	private Optional<RequestDTO> requestDTO;

	@BeforeEach
	public void config() {
		phoneDTO = new PhoneDTO(new Long(12345678), 1, "2");
		List<PhoneDTO> listPhoneDTO = new ArrayList<>();
		listPhoneDTO.add(phoneDTO);

		userDTO = Optional.ofNullable(new UserDTO("Juan Ejemplo", "juan@ejemplo.cl", "Password22", listPhoneDTO));
		requestDTO = Optional.ofNullable(new RequestDTO("Juan Ejemplo", "juan@ejemplo.cl", "Password22", listPhoneDTO));
	}

	@Test
	void testCreateUser() {
		when(this.userService.createUser(this.requestDTO.get())).thenReturn(this.userDTO);
		final ResponseEntity<UserDTO> response = userController.createUser(this.requestDTO.get());

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(this.userDTO.get(), response.getBody());
	}

}