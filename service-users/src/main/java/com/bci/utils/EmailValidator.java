package com.bci.utils;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class EmailValidator {

	private static final String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
		        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	
	
	public boolean validate(String email) { 
	 return Pattern.compile(regexPattern)
		      .matcher(email)
		      .matches();
	}
	
}
