package com.rodolfo.infrastructure.exceptions;

public class ConflictExecption extends RuntimeException {

	private static final long serialVersionUID = 8873054071749949576L;

	public ConflictExecption(String message) {
		super(message);
	}

	public ConflictExecption(String message, Throwable cause) {
		super(message, cause);
	}

	
	
	
}
