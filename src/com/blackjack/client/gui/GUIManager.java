package com.blackjack.client.gui;

import com.blackjack.testing.FakeClientGUITester; // Dummy CLient to test GUI while Client.java doesn't exist yet


// components for GUI
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class GUIManager {

	private FakeClientGUITester client;										 // reference to client for GUI to send messages
	private String currentScreen;								 // variable for tracking which screen is currently active for user
	
	JFrame frame;												 // main window
	JPanel mainPanel, 											 // holds all differnt screens (login, lobby, table)
		   loginPanel, lobbyPanel, tablePanel;  				 // individual screens
	
	JLabel statusLbl, balanceLbl, currBetLbl, pHandLbl, dHandLbl;// table screen labels
	
	public GUIManager(FakeClientGUITester client) {
		
		this.client = client; // reference to specific client
		
		// Create a frame; a window displaying to the user
		frame = new JFrame("BlackJack Game");	
		frame.setSize(1100,700);		       				  // set window size length x width
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make it so the window closes at exit
		
		// Create main panel that will hold the 3 different screens (login, lobby, table)
		mainPanel = new JPanel();					
		mainPanel.setLayout(new CardLayout()); 				  // Use CardLayout layout for switching between multiple views
		
		// add screens to the main panel (used for switching between screens)
		loginPanel = createLoginPanel();
		lobbyPanel = createLobbyPanel();
		tablePanel = createTablePanel();
		
		// add main panel to frame/window
		frame.add(mainPanel);
		
		//Set visibility to true to display on screen
		frame.setVisible(true);
		
		showLoginScreen(); 								     // starting screen to play game (login screen)
	}
	
	/****************************************************
	 * 				CREATE DIFFERENT SCREENS
	 ****************************************************/
	private JPanel createLoginPanel() 
	{
		
		// return the panel
	}
	
	private JPanel createLobbyPanel() 
	{
		
		// return the panel
	}
	
	private JPanel createTablePanel() 
	{
		
		// return the panel
	}
	
	/****************************************************
	 * 				SWITCH BETWEEN SCREENS
	 ****************************************************/
	
	public void showLoginScreen() {
		currentScreen = "LOGIN";			// updates the current screen state to login screen
		((CardLayout) mainPanel.getLayout()).show(mainPanel, "LOGIN");
	}	
	
	public void showLobbyScreen() {
		
	}
	
	public void showTableScreen() {
		
	}
	
	/****************************************************
	 * 		UPDATE IN GAME STATUS DISPLAY METHODS 
	 ****************************************************/
	
	public void displayStatus(String message) {
		
	}
	
	public void displayBalance(double amount) {
		
	}
	
	public void displayCurrentBet(double amount) {
		
	}
	
	public void displayPlayerHand(String cards, int total) {
		
	}
	
	public void displayDealerHand(String cards, int total) {
		
	}
	
	public void displayRoundResult(String result) {
		
	}
	
}
