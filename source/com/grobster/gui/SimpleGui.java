package com.grobster.gui;

import javax.swing.*;
import java.awt.*;

/**
 * SimpleGui is a concrete base class, used to create program GUI's.
 *
 * @author Jeffery Quarles
 * @version 1.0
 * @since 1.0
 */
public class SimpleGui {
	private String programName;
	private int frameWidth;
	private int frameHeight;
	private JFrame frame;
	private JPanel mainPanel;
	
	//constructors
	
	
	/**
	 * Creats SimpleGui object with @param programName, @param frameWidth, @param frameHeight.
	 *
	 * @param programName sets the name of the JFrame
	 * @param frameWidth sets the width of JFrame
	 * @param frameHeight sets height of JFrame
	 */
	public SimpleGui(String programName, int frameWidth, int frameHeight) {
		this.programName = programName;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		frame = new JFrame(programName);
		mainPanel = new JPanel();
	}
	
	public SimpleGui(String programName) {
		this.programName = programName;
		frame = new JFrame(programName);
		mainPanel = new JPanel();
	}
	
	public SimpleGui(int frameWidth, int frameHeight) {
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		frame = new JFrame(programName);
		mainPanel = new JPanel();
	}
	
	public SimpleGui() {
		frame = new JFrame(programName);
		mainPanel = new JPanel();
	}
	
	/**
	 * creates the view of the program
	 */
	public void createView() {
		frame.setSize(frameWidth, frameHeight);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	//static methods
	
	/**
	 * Used to create a JPanel that holds a Jcomponent with a label name
	 *
	 * @param lableName the name of the label assocatied with the JComponent being added to the JPanel
	 * @param component the type of JComponent being added to a JPanel
	 * @return the JPanel with the JLabel and JComponent added to it
	 */
	public static JComponent addComponentWithLabel(String labelName, JComponent component) {
		JPanel inputPanel = new JPanel();
		JLabel label = new JLabel(labelName);
		inputPanel.add(label);
		inputPanel.add(component);
		
		return inputPanel;
	}
	
	/**
	 * Used to create a JPanel that holds only a JComponent
	 *
	 * @param component the type of JComponent being added to a JPanel
	 * @return the JPanel with the JComponent added to it.
	 */
	public static JComponent addComponentNoLabel(JComponent component) {
		JPanel inputPanel = new JPanel();
		inputPanel.add(component);
		
		return inputPanel;
	}
	
	//setters
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	
	public void setFrameWidth(int frameWidth) {
		this.frameWidth = frameWidth;
	}
	
	public void setFrameHeight(int frameHeight) {
		this.frameHeight = frameHeight;
	}
	
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}
	
	//getters
	
	public JFrame getFrame() {
		return frame;
	}
	
	public String getProgramName() {
		return programName;
	}
	
	public int getFrameWidth() {
		return frameWidth;
	}
	
	public int getFrameHeight() {
		return frameHeight;
	}
	
	public JPanel getMainPanel() {
		return mainPanel;
	}
}