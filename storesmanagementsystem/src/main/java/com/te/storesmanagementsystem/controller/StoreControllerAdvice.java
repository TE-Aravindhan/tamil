package com.te.storesmanagementsystem.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import com.te.storesmanagementsystem.beans.Response;
import com.te.storesmanagementsystem.customexception.StoreIdNotFoundException;

@RestControllerAdvice
public class StoreControllerAdvice {

	@ExceptionHandler(StoreIdNotFoundException.class)
	public ResponseEntity<Response> toHandleSIDNotFound(StoreIdNotFoundException exception) {
		Response response = new Response(true, exception.getMessage());
		return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<Response> toHandleIO() {
		Response response = new Response(true, "Please Upload Valid CSV file");
		return new ResponseEntity<Response>(response, HttpStatus.NOT_ACCEPTABLE);

	}
	
	@ExceptionHandler(MissingServletRequestPartException.class)
	public ResponseEntity<Response> toHandleMSPE() {
		Response response = new Response(true,"Please Upload CVS file" );
		return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<Response> toHandleMSPRE() {
		Response response = new Response(true,"Please provide all the details" );
		return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);

	}

}
