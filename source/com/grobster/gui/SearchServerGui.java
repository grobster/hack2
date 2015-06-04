package com.grobster.gui;

import java.awt.event.*;
import java.awt.*;
import com.grobster.hack.*;
import com.grobster.net.*;
import java.nio.file.*;
import javax.swing.*;

public class SearchServerGui implements ActionListener {
	private FileSearchServer server;
	private ServerGui gui;
	
	public SearchServerGui(ServerGui gui, FileSearchServer server) {
		this.server = server;
		this.gui = gui;
	}
	
	public void createView(int fieldSize, int portFieldSize, String buttonName, String outputString, String portString) {
		gui.createView(fieldSize, portFieldSize, buttonName, outputString, portString);
		gui.getRunButton().addActionListener(this);
		gui.getStopServerButton().addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == gui.getRunButton()) {
			Path p = Paths.get(gui.getOutputField().getText());
			int port = Integer.parseInt(gui.getPortField().getText());
			server.setDirPath(p);
			server.setPort(port);
			gui.clearTextFields();
			gui.getPortRunningLabel().setText(ServerGui.PORT_RUNNING_STRING + server.getPort());
			SwingWorker sw = new SwingWorker() {
				public Object doInBackground() {
					server.run();
					return null;
				}
			};
			sw.execute();
			
			//server.run();
		} else if (e.getSource() == gui.getStopServerButton()) {
			throw new ServerStoppedException("Server Stopped");
		}
	}
	
	public static void main(String[] args) {
		ServerGui gui = new ServerGui("File Search Server", 300, 300);
		Path ouputPath = Paths.get("C:\\");
		FileSearchServer server = new FileSearchServer(4242, new FileNamerJpeg(), ouputPath);
		SearchServerGui ssg = new SearchServerGui(gui, server);
		ssg.createView(20, 10, "Run", "Output Directory: ", "Port: ");
	}
}