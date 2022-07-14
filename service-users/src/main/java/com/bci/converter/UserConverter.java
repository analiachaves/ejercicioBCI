package com.bci.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bci.dto.PhoneDTO;
import com.bci.dto.RequestDTO;
import com.bci.dto.UserDTO;
import com.bci.entity.PhoneEntity;
import com.bci.entity.UserEntity;

@Component
public class UserConverter {

	@Autowired
	private PhoneConverter phoneConverter;

	public UserDTO userEntityToDTO(UserEntity entity) {

		List<PhoneDTO> phones = entity.getPhones().stream().map(phone -> phoneConverter.phoneEntityToDTO(phone))
				.collect(Collectors.toList());

		UserDTO dto = new UserDTO(entity.getName(), entity.getEmail(), entity.getPassword(), phones);

		dto.setId(entity.getId());

		dto.setCreated(entity.getCreated());
		dto.setIsActive(entity.getIsActive());
		dto.setLastLogin(entity.getLastLogin());
		dto.setToken(entity.getToken());

		return dto;

	}

	public UserEntity userDTOToEntity(UserDTO dto) {

		UserEntity entity = new UserEntity();
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		List<PhoneEntity> phones = dto.getPhones().stream().map(phone -> phoneConverter.phoneDTOToEntity(phone))
				.collect(Collectors.toList());
		entity.setPhones(phones);
		entity.setCreated(dto.getCreated());
		entity.setLastLogin(dto.getLastLogin());
		entity.setToken(dto.getToken());

		return entity;

	}

	public UserEntity requestDTOToEntity(RequestDTO dto) {

		UserEntity entity = new UserEntity();
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		List<PhoneEntity> phones = dto.getPhones().stream().map(phone -> phoneConverter.phoneDTOToEntity(phone))
				.collect(Collectors.toList());
		entity.setPhones(phones);

		return entity;

	}

}
