package com.bci.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bci.dto.LoginRequestDTO;
import com.bci.dto.RequestDTO;
import com.bci.dto.UserDTO;
import com.bci.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/sign-up")
	public ResponseEntity<UserDTO> createUser(@RequestBody RequestDTO request) {
	    final Optional<UserDTO> result = userService.createUser(request);
		return new ResponseEntity<>(result.get(), HttpStatus.CREATED);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@RequestBody LoginRequestDTO request) {
		final Optional<UserDTO> result = userService.login(request);
		return new ResponseEntity<>(result.get(), HttpStatus.FOUND);
	}
	
}
