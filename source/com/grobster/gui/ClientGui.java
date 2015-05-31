package com.grobster.gui;

import javax.swing.*;
import java.awt.*;

public class ClientGui extends SimpleGui {
	private JTextField hostField;
	private JTextField portField;
	private JTextField pathField;
	private JButton enterButton;
	
	public ClientGui(String programName, int frameWidth, int frameHeight) {
		super(programName, frameWidth, frameHeight);
	}
	
	public ClientGui() {
		super();
	}
	
	public void createView(int fieldSize, String buttonName, String hostString, String portString, String pathString) {
		//create widgets
		hostField = new JTextField(fieldSize);
		portField = new JTextField(fieldSize);
		pathField = new JTextField(fieldSize);
		enterButton = new JButton(buttonName);
		
		//create JPanels to hold widgets
		JComponent hostComponent = SimpleGui.addComponentWithLabel(hostString, hostField);
		JComponent portComponent = SimpleGui.addComponentWithLabel(portString, portField);
		JComponent pathComponent = SimpleGui.addComponentWithLabel(pathString, pathField);
		JComponent enterButtonComponent = SimpleGui.addComponentNoLabel(enterButton);
		
		//add JPanels to mainProgramPanel
		getMainPanel().add(hostComponent);
		getMainPanel().add(portComponent);
		getMainPanel().add(pathComponent);
		getMainPanel().add(enterButtonComponent);
		
		createView();
	}
	
	//getters
	public JTextField getHostField() {
		return hostField;
	}
	
	public JTextField getPortField() {
		return portField;
	}
	
	public JTextField getPathField() {
		return pathField;
	}
	
	public JButton getEnterButton() {
		return enterButton;
	}
	
	public static void main(String[] args) {
		ClientGui gui = new ClientGui("Client", 300, 300);
		gui.createView(20, "Enter", "Host: ", "Port: ", "Path: ");
	}
}