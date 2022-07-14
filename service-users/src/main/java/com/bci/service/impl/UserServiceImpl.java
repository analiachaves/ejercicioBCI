package com.bci.service.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bci.converter.UserConverter;
import com.bci.dto.RequestDTO;
import com.bci.dto.UserDTO;
import com.bci.entity.UserEntity;
import com.bci.error.EmailErrorException;
import com.bci.error.EmailValidationErrorException;
import com.bci.error.PasswordValidationErrorException;
import com.bci.error.UserNotFoundException;
import com.bci.repository.UserRepository;
import com.bci.service.UserService;
import com.bci.utils.EmailValidator;
import com.bci.utils.PasswordValidator;
import com.bci.utils.TokenGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private EmailValidator emailValidator;

	@Autowired
	private PasswordValidator passwordValidator;

	@Autowired
	private TokenGenerator tokenGenerator;

	@Override
	public Optional<UserDTO> createUser(RequestDTO user) {

		logger.info("Init create user");

		validateEmail(user.getEmail());
		validatePassword(user.getPassword());

		UserEntity userEntity = getNewUserEntity(user);

		try {
			userEntity = userRepository.save(userEntity);
		} catch (DataIntegrityViolationException e) {
			throw new EmailErrorException();
		} catch (Exception e) {
			throw e;
		}
		logger.info("End create user");
		return Optional.ofNullable(userConverter.userEntityToDTO(userEntity));

	}

	@Override
	public Optional<List<UserDTO>> getUsers() {
		return null; // userRepository.findAll();
	}

	@Override
	public void deleteUser(Integer userId) {
		userRepository.deleteById(userId);

	}

	@Override
	public Optional<UserDTO> getUser(Integer userId) {
		return null; // userRepository.findById(userId);
	}

	@Override
	public Optional<UserDTO> getUserByEmail(String email) {
		logger.info("Init get user by email: " + email);
		Optional<UserEntity> userEntity = this.userRepository.findByEmail(email);

		if (!userEntity.isPresent()) {
			throw new UserNotFoundException();
		}
		logger.info("End get user by email: " + email);
		return Optional.ofNullable(userConverter.userEntityToDTO(userEntity.get()));
	}

	@Override
	public void updateToken(String userId, String token) {
		// TODO Auto-generated method stub

	}

	private void validateEmail(String email) {

		if (!emailValidator.validate(email)) {
			throw new EmailValidationErrorException();
		}
	}

	private void validatePassword(String password) {

		if (!passwordValidator.validate(password)) {
			throw new PasswordValidationErrorException();
		}
	}

	private UserEntity getNewUserEntity(RequestDTO user) {

		UserEntity userEntity = userConverter.requestDTOToEntity(user);
		userEntity.setCreated(LocalDateTime.now());
		userEntity.setLastLogin(LocalDateTime.now());
		userEntity.setIsActive(true);

		userEntity.setToken(tokenGenerator.getJWTToken(user.getEmail()));

		userEntity.getPhones().forEach(phone -> phone.setPhoneUser(userEntity));

		return userEntity;
	}

}
