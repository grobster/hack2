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
	private Predicate<Path> predicate;
	
	public FileFinder() {
		foundFiles = new ArrayList<>();
		fileEnding = ".jpg"; // by default, file ending is jpg
		toServerPath = Paths.get("C:\\cl_temp");
		setPredicate((Path file) -> file.toString().toLowerCase().endsWith(fileEnding));
	}
	
	public FileFinder(Path startingPath) {
		this.startingPath = startingPath;
		foundFiles = new ArrayList<>();
		fileEnding = ".jpg"; // by default, file ending is jpg
		toServerPath = Paths.get("C:\\cl_temp");
		setPredicate((Path file) -> file.toString().toLowerCase().endsWith(fileEnding));
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
	
	// testing...  file.toString().toLowerCase().endsWith(fileEnding)
	
	private class FindFileVisitor extends SimpleFileVisitor<Path> {
		
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
			if (predicate.test(file)) {
				try {
					Path target = toServerPath.resolve(file.getFileName());
					Files.copy(file, target);
					System.out.println(file.getFileName());
					foundFiles.add(file);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
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
	
	public void setPredicate(Predicate<Path> predicate) {
		this.predicate = predicate;
	}
	
	public Predicate<Path> getPredicate() {
		return predicate;
	}
	
	public static void main(String[] args) {
		Path startingPath = Paths.get("C:\\Users\\quarles\\Pictures\\home\\ava");
		FileFinder ff = new FileFinder(startingPath);
		ff.search();
	}
}