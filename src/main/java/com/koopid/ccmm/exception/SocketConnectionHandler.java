package com.koopid.ccmm.exception;

public class SocketConnectionHandler extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SocketConnectionHandler(String message) {
		super(message);
	}
}
