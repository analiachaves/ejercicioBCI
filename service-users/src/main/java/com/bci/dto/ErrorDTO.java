package com.bci.dto;

import java.time.LocalDateTime;

public class ErrorDTO {

	private LocalDateTime timestamp;
	private Integer code;
	private String detail;

	public ErrorDTO() {

	}

	public ErrorDTO(LocalDateTime timestamp, Integer code, String detail) {
		super();
		this.timestamp = timestamp;
		this.code = code;
		this.detail = detail;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
