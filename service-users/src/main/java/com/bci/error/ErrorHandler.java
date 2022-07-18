package com.bci.error;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bci.dto.ErrorDTO;
import com.bci.dto.ErrorsDTO;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(EmailValidationErrorException.class)
	protected ResponseEntity<ErrorsDTO> handleException(EmailValidationErrorException ex) {
		return getResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(EmailErrorException.class)
	protected ResponseEntity<ErrorsDTO> handleException(EmailErrorException ex) {
		return getResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(PasswordValidationErrorException.class)
	protected ResponseEntity<ErrorsDTO> handleException(PasswordValidationErrorException ex) {
		return getResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(UserNotFoundException.class)
	protected ResponseEntity<ErrorsDTO> handleException(UserNotFoundException ex) {
		return getResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(PasswordIncorrectErrorException.class)
	protected ResponseEntity<ErrorsDTO> handleException(PasswordIncorrectErrorException ex) {
		return getResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorsDTO> handleException(Exception ex) {
		return getResponse("Unexpected error", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ResponseEntity<ErrorsDTO> getResponse(String msg, HttpStatus status) {
		ErrorDTO errorDTO = new ErrorDTO(LocalDateTime.now(), Integer.valueOf(status.value()), msg);
		ErrorsDTO errorsDTO = new ErrorsDTO();
		errorsDTO.getError().add(errorDTO);
		return new ResponseEntity<>(errorsDTO, status);
	}
}
