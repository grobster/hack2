package com.grobster.hack;

import java.nio.file.*;

public class ClientTestDrive {
	public static void main(String[] args) {
		Path startingPath = Paths.get("C:\\Users\\");
		FileFinder ff = new FileFinder(startingPath);
		ff.search();
		Client client = new Client();
		client.go();
	}
}