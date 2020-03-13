package com.sgb.server.sevice.exception;

public class ViolationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ViolationException(String msg) {
		super(msg);
	}
}
