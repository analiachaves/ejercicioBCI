package com.bci.dto;

import java.time.LocalDateTime;
import java.util.List;

public class UserDTO extends RequestDTO {

	private Long Id;
	private LocalDateTime created;
	private LocalDateTime lastLogin;
	private String token;
	private Boolean isActive;

	public UserDTO() {
	}

	public UserDTO(String name, String email, String password, List<PhoneDTO> phones) {
		super(name, email, password, phones);
	}

	public UserDTO(LocalDateTime created, LocalDateTime lastLogin, String token, Boolean isActive) {
		super();
		this.created = created;
		this.lastLogin = lastLogin;
		this.token = token;
		this.isActive = isActive;
	}

	
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
