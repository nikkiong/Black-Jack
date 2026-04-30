package com.blackjack.client.gui;

import com.blackjack.testing.FakeClientGUITester; // Dummy CLient to test GUI while Client.java doesn't exist yet


// components for GUI
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class GUIManager {

	private FakeClientGUITester client;		 					 // temporary dummy client 								 // reference to client for GUI to send messages
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
		//lobbyPanel = createLobbyPanel();
		//tablePanel = createTablePanel();
		
		mainPanel.add(loginPanel, "LOGIN");
		//mainPanel.add(lobbyPanel, "LOBBY");
		//mainPanel.add(tablePanel, "TABLE");
		
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
		/****************************************************
		 * 						DESIGN
		 ****************************************************/
		// create background of current screen
		ImageIcon og = new ImageIcon(getClass().getResource("/images/background.JPEG"));
		 
		// scale the image to fit onto screen in a good proportion visually
		Image fitToSize = og.getImage().getScaledInstance(1100, 700, Image.SCALE_SMOOTH);
		
		// make the image a label
		JLabel bckgrd = new JLabel(new ImageIcon(fitToSize));
		
		// create the panel
		JPanel gridPanel = new JPanel();
		
		gridPanel.setBackground(new Color(0,80,0));		// set the background of the created panel to a custom color (dark greenish, casino table vibe)
		gridPanel.setBounds(295, 150, 500, 400);		//  set the location + size of panel (x, y, length, width)
	
		
		gridPanel.setLayout(new GridBagLayout());		// create a GridBagLayout for customization
		
		// the layout control for placing components in a GridBagLayout; 
		// where and how each item is placed
		GridBagConstraints layoutControl = new GridBagConstraints();
		
		layoutControl.insets = new Insets(8,8,8,8); 		// adds padding around components (top, left, bottom, right)
		layoutControl.anchor = GridBagConstraints.CENTER;   // centers the following components
		layoutControl.fill = GridBagConstraints.HORIZONTAL; // fill space horizontal
		
		//---------------------------------------------
		// 			CREATE USERNAME FIELDS
		//---------------------------------------------
		
		//------------Username Label-------------------
	    JLabel userTitle = new JLabel("Username");			// make label
	    userTitle.setForeground(Color.WHITE);				// set text color to white
	    layoutControl.gridx = 0;							// (x, y) <=>(0,0) (from top to bottom, ex:    |_0__|_1__|...
	    layoutControl.gridy = 0;							//									  	   0   |____|_____
	    layoutControl.gridwidth = 2;						// width; taking up how many columns	   1   |____|_____		)
	    gridPanel.add(userTitle, layoutControl);			// add label to panel
	    
	    //------------Username Text Field--------------
	    JTextField username = new JTextField(15);			// create a text field that has a capacity of 15
	    layoutControl.gridy = 1;							// go under the label ("Username")
	    gridPanel.add(username, layoutControl);				// add text field to panel
	    
	    //---------------------------------------------
	    // 			CREATE PASSWORD FIELDS
	    //---------------------------------------------
	    
	    //------------Password Label-------------------
	    JLabel paswrdTitle = new JLabel("Password");
	    paswrdTitle.setForeground(Color.WHITE);
	    layoutControl.gridy = 2;
	    gridPanel.add(paswrdTitle, layoutControl);
	    
	    //------------Password Text Field--------------
	    JTextField password = new JTextField(15);
	    layoutControl.gridy = 3;
	    gridPanel.add(password, layoutControl);
	    
	    //---------------------------------------------
	  	// 			CREATE BUTTONS
	  	//---------------------------------------------
	    
	    //------------Sign Up Button-------------------
	    JButton signUpBtn = new JButton("Sign Up");
	    signUpBtn.setForeground(Color.BLACK);
	    layoutControl.gridx = 0;
	    layoutControl.gridy = 4;
	    layoutControl.gridwidth = 1;
	    gridPanel.add(signUpBtn, layoutControl);
	    
	    //------------Login Button---------------------
	    JButton loginBtn = new JButton("Login");
	    loginBtn.setForeground(Color.BLACK);
	    layoutControl.gridx = 1;
	    layoutControl.gridy = 4;
	    layoutControl.gridwidth = 1;
	    gridPanel.add(loginBtn, layoutControl);
	    
		bckgrd.add(gridPanel);
		
		
		JPanel group = new JPanel(new BorderLayout()); // create a panel to return at end of the method
		group.add(bckgrd);							   // add label to panel
		
		/****************************************************
		 * 					ACTION LISTENERS
		 ****************************************************/
		
		
		
		return group;
	}
	
	//private JPanel createLobbyPanel() 
	{
		
		// return the panel
	}
	
	//private JPanel createTablePanel() 
	{
		
		// return the panel
	}
	
	/****************************************************
	 * 				SWITCH BETWEEN SCREENS
	 ****************************************************/
	
	public void showLoginScreen() {

		currentScreen = "LOGIN";										// updates the current screen state to login screen
		((CardLayout) mainPanel.getLayout()).show(mainPanel, "LOGIN");  //
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
