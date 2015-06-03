package com.grobster.net;

import java.net.*;

public abstract class Server {
	private int portNumber;
	private ServerSocket serverSocket;
	
	public Server(int portNumber) {
		this.portNumber = portNumber;
	}
	
	public Server() {}
	
	public abstract void run();
	
	//setters
	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}
	
	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
	//getters
	public int getPortNumber() {
		return portNumber;
	}
	
	public ServerSocket getServerSocket() {
		return serverSocket;
	}
}