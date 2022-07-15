package com.bci.service;

import java.util.Optional;

import com.bci.dto.LoginRequestDTO;
import com.bci.dto.RequestDTO;
import com.bci.dto.UserDTO;

public interface UserService {

	public Optional<UserDTO> createUser(RequestDTO user);

	public Optional<UserDTO> login(LoginRequestDTO request);

}
