package com.shop.projectshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.shop.projectshop.dto.ResponseStructure;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserIdDoesNotPresentException.class)
	public ResponseEntity<ResponseStructure<String>> catchIdDoesNotPresentException(UserIdDoesNotPresentException doesNotPresentException){
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		responseStructure.setMessage("Not Found");
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setData(doesNotPresentException.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
	}
}
