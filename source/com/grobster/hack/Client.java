package com.grobster.hack;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

public class Client {
	private Path toServerPath; // directory that holds files to be transferred to server
	private String host;
	private int port;
	private HashMap<String, Boolean> hm;
	
	public Client() {
		host = "127.0.0.1";
		port = 4242;
		toServerPath = Paths.get(System.getProperty("user.home") + System.getProperty("file.separator") + "cl_temp");
		hm = new HashMap<>();
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
			if (alreadyUploadedCheck(f.getName()) == false) {
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
					
					//add to hashmap here
					hm.put(f.getName(), new Boolean(true));
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
			} // end if
		} // end for loop
		long stop = System.currentTimeMillis();
		long result = stop - start;
		System.out.println("It took the client " + result + " milliseconds to run the for loop");
	}
	
	private boolean alreadyUploadedCheck(String fileName) {
		if (hm.containsKey(fileName)) {
			System.out.println("key found in map");
			return hm.get(fileName).booleanValue();
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