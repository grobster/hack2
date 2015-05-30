package com.grobster.hack;

import java.io.*;
import java.net.*;
import java.nio.file.*;

public class Client {
	File file;
	File[] toServerDirectory;
	private Path toServerPath = Paths.get("C:\\cl_temp");
	private String host;
	private int port;
	
	public Client() {
		host = "127.0.0.1";
		port = 4242;
		createToServerDirectory(); //creates temp folder used to hold jpg prior to sending to server
	}
	
	public void go() {
		File[] files = toServerPath.toFile().listFiles();
		for (File f: files) {
			try {
				Socket s = new Socket(host, port);
				
				//file = new File("C:\\Users\\Android\\Desktop\\pillow\\toserver\\IMAG3215.jpg");
				
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));
				
				int count;
				byte[] buffer =  new byte[1024];
				
				OutputStream os = s.getOutputStream();
				
				while ((count = in.read(buffer)) > 0) {
					os.write(buffer, 0, count);
					os.flush();
				}	
				s.close();
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private boolean createToServerDirectory() {
		try {
			if (!Files.exists(toServerPath)) {
				Files.createDirectory(toServerPath);
				return Files.exists(toServerPath);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	//setters
	public void setToServerPath(Path toServerPath) {
		this.toServerPath = toServerPath;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	//getters
	public Path getToServerPath() {
		return toServerPath;
	}
	
	public String getHost() {
		return host;
	}
	
	public int getPort() {
		return port;
	}

	public static void main(String[] args) {
		Client client = new Client();
		client.go();
	}
}