package com.bci.service.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bci.converter.UserConverter;
import com.bci.dto.LoginRequestDTO;
import com.bci.dto.RequestDTO;
import com.bci.dto.UserDTO;
import com.bci.entity.UserEntity;
import com.bci.error.EmailErrorException;
import com.bci.error.EmailValidationErrorException;
import com.bci.error.PasswordIncorrectErrorException;
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
		user.setPassword(encodePass(user.getPassword()));
		
		UserEntity userEntity = getNewUserEntity(user);

		try {
			userEntity = userRepository.save(userEntity);
		} catch (DataIntegrityViolationException e) {
			throw new EmailErrorException();
		} catch (Exception e) {
			throw e;
		}
		logger.info("End create user");
		UserDTO userDTO = userConverter.userEntityToDTO(userEntity);
		userDTO.setToken(tokenGenerator.getJWTToken(user.getEmail()));

		return Optional.ofNullable(userDTO);

	}

	@Override
	public Optional<UserDTO> login(LoginRequestDTO request) {
		
		
		logger.info("Init get user : " + request.getUser());
		Optional<UserEntity> userEntity = this.userRepository.findByEmail(request.getUser());

		if (!userEntity.isPresent()) {
			throw new UserNotFoundException();
		}
		if(!validatePass(request.getPassword(), userEntity.get().getPassword())) {
			throw new PasswordIncorrectErrorException();
		}
		logger.info("End get user : " + request.getUser());
		return Optional.ofNullable(userConverter.userEntityToDTO(userEntity.get()));
	}

	private void validateEmail(String email) {

		if ((email == null) || (!emailValidator.validate(email))) {
			throw new EmailValidationErrorException();
		}
	}

	private void validatePassword(String password) {

		if ((password == null) || (!passwordValidator.validate(password))) {
			throw new PasswordValidationErrorException();
		}
	}

	private UserEntity getNewUserEntity(RequestDTO user) {

		UserEntity userEntity = userConverter.requestDTOToEntity(user);
		userEntity.setCreated(LocalDateTime.now());
		userEntity.setLastLogin(LocalDateTime.now());
		userEntity.setIsActive(true);

		userEntity.getPhones().forEach(phone -> phone.setPhoneUser(userEntity));

		return userEntity;
	}

	private String encodePass(String userPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
		return encoder.encode(userPassword);
	}
	
	private boolean validatePass(String password, String passwordEncoded) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
		return encoder.matches(password, passwordEncoded);
	}

}
