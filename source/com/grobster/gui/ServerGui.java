package com.grobster.gui;

import javax.swing.*;
import java.awt.*;

public class ServerGui extends SimpleGui {
	private JTextField outputField;
	private JTextField portField;
	private JLabel portRunningLabel;
	private JButton runButton;
	private JButton stopServerButton;
	private JLabel colorLabel;
	public static final String PORT_RUNNING_STRING = "Running on Port: ";
	
	public ServerGui(String programName, int frameWidth, int frameHeight) {
		super(programName, frameWidth, frameHeight);
	}
	
	public ServerGui() {
		super();
	}
	
	public void createView(int fieldSize, int portFieldSize, String buttonName, String outputString, String portString) {
		//create widgets
		outputField = new JTextField(fieldSize);
		portField = new JTextField(portFieldSize);
		portRunningLabel = new JLabel(PORT_RUNNING_STRING + 4242);
		runButton = new JButton(buttonName);
		stopServerButton = new JButton("Stop"); // replace with a parameter instead of using a String literal for button name
		colorLabel = new JLabel("       ");
		colorLabel.setBackground(Color.red);
		colorLabel.setOpaque(true);
		
		//create JPanels to hold widgets
		JComponent outputComponent = SimpleGui.addComponentWithLabel(outputString, outputField);
		JComponent portComponent = SimpleGui.addComponentWithLabel(portString, portField);
		JComponent runningComponent = SimpleGui.addComponentNoLabel(portRunningLabel);
		JComponent runButtonComponent = SimpleGui.addComponentNoLabel(runButton);
		JComponent stopButtonComponent = SimpleGui.addComponentNoLabel(stopServerButton);
		JComponent colorComponent = SimpleGui.addComponentNoLabel(colorLabel);
		
		//add JPanels to mainProgramPanel
		getMainPanel().add(outputComponent);
		getMainPanel().add(portComponent);
		getMainPanel().add(runningComponent);
		getMainPanel().add(runButtonComponent);
		getMainPanel().add(stopButtonComponent);
		getMainPanel().add(colorComponent);
		
		createView();
	}
	
	public void clearTextFields() {
		outputField.setText("");
		portField.setText("");
	}
	
	//getters
	public JTextField getOutputField() {
		return outputField;
	}
	
	public JTextField getPortField() {
		return portField;
	}
	
	public JLabel getPortRunningLabel() {
		return portRunningLabel;
	}
	
	public JButton getRunButton() {
		return runButton;
	}
	
	public JButton getStopServerButton() {
		return stopServerButton;
	}
	
	public JLabel getColorLabel() {
		return colorLabel;
	}
	
	//setters
	public void setColorLabel(JLabel colorLabel) {
		this.colorLabel = colorLabel;
	}
	
	public static void main(String[] args) {
		ServerGui gui = new ServerGui("Server", 300, 300);
		gui.createView(20, 10, "Run", "Output Directory: ", "Port: ");
	}
}