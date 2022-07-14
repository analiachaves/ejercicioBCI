package com.bci.error;

import java.util.Objects;

public class EmailErrorException extends RuntimeException{

	
	private static final long serialVersionUID = 8637860294439288168L;
	
	private static final String MESSAGE_DEFAULT = "Email: duplicated";
	
	@Override
	public String getMessage() {
		if(Objects.nonNull(super.getMessage())){
			return super.getMessage();
		}
		return MESSAGE_DEFAULT;
	}

}
