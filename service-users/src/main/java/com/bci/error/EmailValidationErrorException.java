package com.bci.error;

import java.util.Objects;

public class EmailValidationErrorException extends RuntimeException{

	
	private static final long serialVersionUID = -767789406298868089L;
	
	private static final String MESSAGE_DEFAULT = "Email: format error";
	
	@Override
	public String getMessage() {
		if(Objects.nonNull(super.getMessage())){
			return super.getMessage();
		}
		return MESSAGE_DEFAULT;
	}

}
