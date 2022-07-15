package com.bci.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name= "users")
public class UserEntity implements Serializable{

	private static final long serialVersionUID = 340432333552410211L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String  name;
	@Column(unique = true)
	private String  email;
	private String  password;
	
	@OneToMany(
			mappedBy = "phoneUser",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<PhoneEntity> phones = new ArrayList<>();
	
	private LocalDateTime    created;
	@Column(name = "last_login")
	private LocalDateTime    lastLogin;
	@Column(name = "is_active")
	private Boolean isActive;
	
	public UserEntity() {
	}
		
	
	public UserEntity(Long id, String name, String email, String password, LocalDateTime created,
			LocalDateTime lastLogin, Boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.created = created;
		this.lastLogin = lastLogin;
			this.isActive = isActive;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<PhoneEntity> getPhones() {
		return phones;
	}
	public void setPhones(List<PhoneEntity> phones) {
		this.phones = phones;
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
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
	
	
}
