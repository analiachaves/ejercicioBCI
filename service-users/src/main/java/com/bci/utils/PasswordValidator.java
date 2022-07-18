package com.bci.utils;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {

	private static final String regexPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,12}$";

	public boolean validate(String password) {
		return Pattern.compile(regexPattern).matcher(password).matches();
	}

}
