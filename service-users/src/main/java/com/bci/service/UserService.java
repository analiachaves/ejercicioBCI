package com.bci.service;

import java.util.List;
import java.util.Optional;

import com.bci.dto.RequestDTO;
import com.bci.dto.UserDTO;


public interface UserService {

	public Optional<UserDTO> createUser(RequestDTO user);

	public Optional<List<UserDTO>> getUsers();

	public void deleteUser(Integer userId);

	public Optional<UserDTO> getUser(Integer userId);

	public Optional<UserDTO> getUserByEmail(String email);

	public void updateToken(String userId, String token);

}
