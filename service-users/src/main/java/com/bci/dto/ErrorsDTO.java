package com.bci.dto;

import java.util.ArrayList;
import java.util.List;

public class ErrorsDTO {

	private List<ErrorDTO> error = new ArrayList<>();

	public ErrorsDTO() {

	}

	public List<ErrorDTO> getError() {
		return error;
	}

	public void setError(List<ErrorDTO> error) {
		this.error = error;
	}

}
