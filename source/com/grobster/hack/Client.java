package com.grobster.hack;

import java.io.*;
import java.net.*;
import java.nio.file.*;

public class Client {
	File file;
	File[] toServerDirectory;
	
	public void go() {
		try {
			Socket s = new Socket("127.0.0.1", 4242);
			
			file = new File("C:\\Users\\Android\\Desktop\\pillow\\toserver\\IMAG3215.jpg");
			
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
			
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

	public static void main(String[] args) {
		Client client = new Client();
		client.go();
	}
}