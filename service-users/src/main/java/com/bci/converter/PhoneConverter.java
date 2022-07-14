package com.bci.converter;

import org.springframework.stereotype.Component;

import com.bci.dto.PhoneDTO;
import com.bci.entity.PhoneEntity;

@Component
public class PhoneConverter {

	public PhoneDTO phoneEntityToDTO(PhoneEntity entity) {
		PhoneDTO dto = new PhoneDTO(entity.getNumber(), entity.getCityCode(), entity.getCountryCode());
		return dto;

	}

	public PhoneEntity phoneDTOToEntity(PhoneDTO dto) {

		PhoneEntity entity = new PhoneEntity();
		entity.setNumber(dto.getNumber());
		entity.setCityCode(dto.getCityCode());
		entity.setCountryCode(dto.getCountryCode());
		return entity;

	}
}
