package com.bci.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bci.dto.ErrorDTO;

@ControllerAdvice
public class ErrorHandler {

    
    @ExceptionHandler(EmailValidationErrorException.class)
    protected ResponseEntity<ErrorDTO> handleException(EmailValidationErrorException ex) {
      	return getResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    	
    }
    
    @ExceptionHandler(EmailErrorException.class)
    protected ResponseEntity<ErrorDTO> handleException(EmailErrorException ex) {
      	return getResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    	
    }
    
    @ExceptionHandler(PasswordValidationErrorException.class)
    protected ResponseEntity<ErrorDTO> handleException(PasswordValidationErrorException ex) {
      	return getResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    	
    }
    
    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<ErrorDTO> handleException(UserNotFoundException ex) {
      	return getResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    	
    }
    
    
    private ResponseEntity<ErrorDTO> getResponse(  String msg, HttpStatus status) {
    	ErrorDTO errorDTO = new ErrorDTO(LocalDateTime.now(), Integer.valueOf(status.value()) ,msg);
    	return new ResponseEntity<>(errorDTO, status); 
    }
}
