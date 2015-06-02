package com.grobster.hack;

import com.grobster.net.*;
import java.io.*;
import java.nio.file.*;
import java.net.*;

public class FileSearchServer extends Server {
	final public static Path SERVER_REC_DIRECTORY = Paths.get("C:\\srec_temp");
	private FileNamerJpeg fileNamer;
	private Path dirPath;
	private int port;
	
	public FileSearchServer(int port, FileNamerJpeg fileNamer, Path dirPath) {
		this.port = port;
		this.fileNamer = fileNamer;
		this.dirPath = dirPath;
		makeTempDirectory();
		setDirPath(SERVER_REC_DIRECTORY);
		setPort(4242);
	}
	
	public FileSearchServer(FileNamerJpeg fileNamer, Path dirPath) {
		this.fileNamer = fileNamer;
		this.dirPath = dirPath;
		makeTempDirectory();
		setDirPath(SERVER_REC_DIRECTORY);
		setPort(4242);
	}
	
	private boolean makeTempDirectory() {
		try {
			if (!Files.exists(SERVER_REC_DIRECTORY)) {
				Files.createDirectory(SERVER_REC_DIRECTORY);
				return Files.exists(SERVER_REC_DIRECTORY);
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
	
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(getPort());
			System.out.println("server running on port: " + getPort());
			while (true) {
				
				Socket s = serverSocket.accept();	
				
				System.out.println("file received ...");
				
				Path p = fileNamer.nameFile(dirPath);
				
				FileOutputStream fos = new FileOutputStream(p.toFile());
				BufferedOutputStream out = new BufferedOutputStream(fos);
				byte[] buffer = new byte[1024];
				int count;
				InputStream in = s.getInputStream();
				
				while((count = in.read(buffer)) >= 0){
					fos.write(buffer, 0, count);
				}
				fos.close();
			}	
		} catch(Exception ex) {
			
			ex.printStackTrace();
		}
	}
	
	public void setDirPath(Path dirPath) {
		this.dirPath = dirPath;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public int getPort() {
		return port;
	}
	
	public Path getDirPath() {
		return dirPath;
	}
	
	public static void main(String[] args) {
		FileSearchServer ffs = new FileSearchServer(new FileNamerJpeg(), FileSearchServer.SERVER_REC_DIRECTORY);
		ffs.run();
	}
}