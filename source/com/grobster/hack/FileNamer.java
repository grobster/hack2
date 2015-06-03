package com.grobster.hack;

import java.nio.file.*;
import java.io.*;

public class FileNamer {
	final public static String IMG_BASE_FILE_NAME = "img";
	final public static String JPEG_ENDING = ".jpg";
	private String fileEnding;
	private String fileNameBase;
	
	public Path nameFile(Path directoryOfFile) {
		if (Files.exists(directoryOfFile) && Files.isDirectory(directoryOfFile)) {
			File[] directoryAll = directoryOfFile.toFile().listFiles();
			int directorySize = directoryAll.length;
			Path p = Paths.get(fileNameBase + directorySize++ + fileEnding);
			Path finalPath = directoryOfFile.resolve(p);
			System.out.println(finalPath);
			return finalPath;
		}
		return null;
	}
	
	public static Path nameFileAlreadyExists(Path toPath) {
		String[] tokens = toPath.toString().split("\\.(?=[^\\.]+$)");
		int count = 0;
		Path pathWithCount = Paths.get(tokens[0] + ++count + "." + tokens[1]);
		
		if (!Files.exists(pathWithCount)) {
			return pathWithCount;
		} else {
			while (Files.exists(pathWithCount)) {
				pathWithCount = Paths.get(tokens[0] + ++count + "." + tokens[1]);
				System.out.println("creating new path: " + pathWithCount.toString());
				if (!Files.exists(pathWithCount)) {
					return pathWithCount;
				}
				System.out.println("in loop...");
			}
		}

		return null;
	}
	
	//setters
	public void setFileEnding(String fileEnding) {
		this.fileEnding = fileEnding;
	}
	
	public void setFileNameBase(String fileNameBase) {
		this.fileNameBase = fileNameBase;
	}
	
	//getters
	public String getFileEnding() {
		return fileEnding;
	}
	
	public String getFileNameBase() {
		return fileNameBase;
	}
	
	public static void main(String[] args) {
		Path testPath = Paths.get("C:\\Users\\quarles\\Documents\\bike\\bike.html");
		Path revisedPath = FileNamer.nameFileAlreadyExists(testPath);
		System.out.println(revisedPath);
	}
}