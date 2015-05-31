package com.grobster.hack;

import java.nio.file.*;

public class ClientTestDrive {
	public static void main(String[] args) {
		Path startingPath = Paths.get("C:\\Users\\Android\\Pictures\\bike");
		FileFinder ff = new FileFinder(startingPath);
		ff.search();
		Client client = new Client();
		client.setHost("127.0.0.1");
		client.go();
	}
}