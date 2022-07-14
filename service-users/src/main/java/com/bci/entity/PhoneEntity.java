package com.bci.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "phone")
public class PhoneEntity implements Serializable {

	private static final long serialVersionUID = -4869101350947791431L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long number;
	@Column(name = "city_code")
	private Integer cityCode;
	@Column(name = "country_code")
	private String countryCode;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity phoneUser;

	public PhoneEntity() {
		super();

	}

	public PhoneEntity(Long id, Long number, Integer cityCode, String countryCode, UserEntity phoneUser) {
		super();
		this.id = id;
		this.number = number;
		this.cityCode = cityCode;
		this.countryCode = countryCode;
		this.phoneUser = phoneUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Integer getCityCode() {
		return cityCode;
	}

	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public UserEntity getPhoneUser() {
		return phoneUser;
	}

	public void setPhoneUser(UserEntity phoneUser) {
		this.phoneUser = phoneUser;
	}

}
