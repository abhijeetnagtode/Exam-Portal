package com.exam.exceptionhandler;

public class NoRecordFoundException extends RuntimeException {

	public NoRecordFoundException(String msg) {
		super(msg);
	}

}
