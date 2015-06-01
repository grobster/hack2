package com.grobster.hack;

public class ServerStoppedException extends RuntimeException {
	public ServerStoppedException(String message) {
		super(message);
	}
}