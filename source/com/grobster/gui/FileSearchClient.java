package com.grobster.gui;

import java.awt.event.*;
import java.awt.*;
import com.grobster.hack.*;
import java.nio.file.*;
import javax.swing.*;

public class FileSearchClient implements ActionListener {
	private ClientGui gui;
	private Client client;
	private FileFinder finder;
	
	public FileSearchClient(ClientGui gui, Client client, FileFinder finder) {
		this.gui = gui;
		this.client = client;
		this.finder = finder;
	}
	
	public void createView(int fieldSize, String buttonName, String hostString, String portString, String pathString) {
		gui.createView(fieldSize, buttonName, hostString, portString, pathString);
		gui.getEnterButton().addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == gui.getEnterButton()) {
			String host = gui.getHostField().getText();
			int port = Integer.parseInt(gui.getPortField().getText());
			String path = gui.getPathField().getText();
			
			Path start = Paths.get(path);
			finder.setStartingPath(start);
			client.setPort(port);
			client.setHost(host);
			
			finder.search();
			SwingWorker sw = new SwingWorker() {
				public Object doInBackground() {
					client.go();
					return null;
				}
			};
			sw.execute();
			//client.go();
		}
	}
	
	public static void main(String[] args) {
		FileNamer.createToServerDirectory();
		ClientGui gui = new ClientGui("Client", 300, 300);
		FileFinder ff = new FileFinder();
		Client client = new Client();
		FileSearchClient clientGui = new FileSearchClient(gui, client, ff);
		clientGui.createView(20, "Enter", "Host: ", "Port: ", "Path: ");
	}
}