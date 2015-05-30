package com.grobster.hack;

import java.nio.file.*;
import java.util.*;
import java.util.function.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.io.*;

public class FileFinder {
	private Path startingPath;
	private ArrayList<Path> foundFiles;
	private String fileEnding;
	private Path toServerPath;
	private FileNamerJpeg fileNamer;
	
	public FileFinder() {
		foundFiles = new ArrayList<>();
		fileEnding = ".jpg"; // by default, file ending is jpg
		toServerPath = Paths.get("C:\\cl_temp");
		fileNamer = new FileNamerJpeg();
	}
	
	public FileFinder(Path startingPath) {
		this.startingPath = startingPath;
		foundFiles = new ArrayList<>();
		fileEnding = ".jpg"; // by default, file ending is jpg
		toServerPath = Paths.get("C:\\cl_temp");
		fileNamer = new FileNamerJpeg();
	}
	
	public void setStartingPath(Path startingPath) {
		this.startingPath = startingPath;
	}
	
	public Path getStartingPath() {
		return startingPath;
	}
	
	public void search() {
		try {
			Files.walkFileTree(startingPath, new FindFileVisitor());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	private class FindFileVisitor extends SimpleFileVisitor<Path> {
		
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
			if (file.toString().toLowerCase().endsWith(fileEnding)) {
				System.out.println(file.getFileName());
				foundFiles.add(file);
			}
			return FileVisitResult.CONTINUE;
		}
	}
	
	public void setToServerPath(Path toServerPath) {
		this.toServerPath = toServerPath;
	}
	
	public Path getToServerPath() {
		return toServerPath;
	}
	
	public String getfileEnding() {
		return fileEnding;
	}
	
	public ArrayList<Path> getFoundFiles() {
		return foundFiles;
	}
	
	public void setFileEnding(String fileEnding) {
		this.fileEnding = fileEnding;
	}
}