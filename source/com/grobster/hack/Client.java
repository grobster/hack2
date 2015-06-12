package com.grobster.hack;

import java.io.*;
import java.net.*;
import java.nio.file.*;

public class Client {
	private Path toServerPath; // directory that holds files to be transferred to server
	private String host;
	private int port;
	
	public Client() {
		host = "127.0.0.1";
		port = 4242;
		createToServerDirectory(); //creates temp folder used to hold jpg prior to sending to server
	}
	
	public void go() {
		File[] files = null;
		
		try {
			files = toServerPath.toFile().listFiles();
		} catch (SecurityException ex) {
			System.out.println("security exception");
		}
		
		Socket s = null;
		long start = System.currentTimeMillis();
		
		for (File f: files) {
			try {
				s = new Socket(host, port);
			} catch (UnknownHostException ex) {
				System.out.println("IP Address could not be determined");
			} catch (IOException ex) {
				System.out.println("IO error");
			} catch (SecurityException ex) {
				System.out.println("security exception");
			} catch (IllegalArgumentException ex) {
				System.out.println("Port must be number between 0 and 65535 inclusive");
			}
			
			BufferedInputStream in = null;
			
			try {
				in = new BufferedInputStream(new FileInputStream(f));
			} catch (FileNotFoundException ex) {
				System.out.println("File not found");
			} catch (SecurityException ex) {
				System.out.println("Security Exception");
			}
				
			int count;
			byte[] buffer =  new byte[4 * 1024];
			
			BufferedOutputStream bos = null;
			
			try {
				bos = new BufferedOutputStream(s.getOutputStream());
			} catch(IOException ex) {
				System.out.println("IO error");
			}
			
			try {	
				while ((count = in.read(buffer)) > 0) {
					bos.write(buffer, 0, count);
					bos.flush();
				}
			} catch (IOException ex) {
				System.out.println("IO error");
			} catch (NullPointerException ex) {
				System.out.println("there are no bytes to write");
			} finally {
				try {
					bos.close();
					in.close();
				} catch(IOException ex) {
					System.out.println("IO error");
				}
			}
		} // end for loop
		long stop = System.currentTimeMillis();
		long result = stop - start;
		System.out.println("It took the client " + result + " milliseconds to run the for loop");
	}
	
	private boolean createToServerDirectory() {
		String userHome = System.getProperty("user.home");
		String separator = System.getProperty("file.separator");
		toServerPath = Paths.get(userHome + separator + "cl_temp");
		try {
			if (!Files.exists(toServerPath)) {
				Files.createDirectory(toServerPath);
				return Files.exists(toServerPath);
			}
		} catch (SecurityException ex) {
			System.out.println("Security Exception");
		} catch (UnsupportedOperationException ex) {
			System.out.println("unsupported operation");
		} catch (FileAlreadyExistsException ex) {
			System.out.println("file already exists");
		} catch (IOException ex) {
			System.out.println("IO error");
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
}