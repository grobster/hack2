package com.grobster.hack;

import com.grobster.net.*;
import java.io.*;
import java.nio.file.*;
import java.net.*;

public class FileSearchServer extends Server {
	final public static Path SERVER_REC_DIRECTORY = Paths.get("C:\\srec_temp");
	private FileNamerJpeg fileNamer;
	private Path dirPath;
	
	public FileSearchServer(int portNumber, FileNamerJpeg fileNamer, Path dirPath) {
		super(portNumber);
		this.fileNamer = fileNamer;
		this.dirPath = dirPath;
		makeTempDirectory();
		setDirPath(SERVER_REC_DIRECTORY);
	}
	
	public FileSearchServer(FileNamerJpeg fileNamer, Path dirPath) {
		this.fileNamer = fileNamer;
		this.dirPath = dirPath;
		makeTempDirectory();
		setDirPath(SERVER_REC_DIRECTORY);
	}
	
	private boolean makeTempDirectory() {
		try {
			if (!Files.exists(SERVER_REC_DIRECTORY)) {
				Files.createDirectory(SERVER_REC_DIRECTORY);
				return Files.exists(SERVER_REC_DIRECTORY);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(4242);
			System.out.println("server running...");
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
				//socket.close();
			}	
		} catch(Exception ex) {
			
			ex.printStackTrace();
		}
	}
	
	public void setDirPath(Path dirPath) {
		this.dirPath = dirPath;
	}
	
	public static void main(String[] args) {
		//Path p = Paths.get("");
		FileSearchServer ffs = new FileSearchServer(new FileNamerJpeg(), FileSearchServer.SERVER_REC_DIRECTORY);
		ffs.run();
	}
}