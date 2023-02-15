package com.exam.exceptionhandler;

import java.util.Date;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandle {
	@ExceptionHandler(NoRecordFoundException.class)
	public ResponseEntity<HashMap<String, Object>> noRecordFound(NoRecordFoundException exception) {
		HashMap<String, Object> hashmap = new HashMap<>();
		hashmap.put("Date", new Date());
		hashmap.put("Message", exception.getMessage());
		return new ResponseEntity<HashMap<String, Object>>(hashmap, HttpStatus.FORBIDDEN);

	}

}
