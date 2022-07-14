package com.bci.error;

import java.util.Objects;

public class UserNotFoundException extends RuntimeException{

	
	private static final long serialVersionUID = -5606301518005248679L;
	
	
	private static final String MESSAGE_DEFAULT = "User not found";
	
	@Override
	public String getMessage() {
		if(Objects.nonNull(super.getMessage())){
			return super.getMessage();
		}
		return MESSAGE_DEFAULT;
	}

}
