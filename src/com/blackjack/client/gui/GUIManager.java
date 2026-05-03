package com.blackjack.client.gui;

import com.blackjack.testing.FakeClientGUITester; // Dummy CLient to test GUI while Client.java doesn't exist yet


// components for GUI
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class GUIManager {

	private FakeClientGUITester client;		 					 // temporary dummy client 								 // reference to client for GUI to send messages
	
	/***********TEMPORARY LISTS FOR LOBBY + TABLE PLAYERS BEFORE CLIENT/SERVER CLASSES MADE*********
	 * for testing purposes
	 */
	private DefaultListModel<String> tableContainer, playersContainer;
	private JList<String> tableList;
	/***********************************************************************************************/
	
	private String currentScreen;								 // variable for tracking which screen is currently active for user
	
	private boolean hasBet = false; 							 // variable for tracking if player placed a bet or not 
	
	JFrame frame;												 // main window
	JPanel mainPanel, 											 // holds all differnt screens (login, lobby, table)
		   loginPanel, signUpPanel, playerlobbyPanel, tablePanel,
		   playerTablePanel, tableScreenPanel, playerCards, dealerCards;  				 // individual screens
	
	JLabel statusLbl, balanceLbl, currBetLbl, pHandLbl, dHandLbl, faceDownCard;// table screen labels
	
	
	public GUIManager(FakeClientGUITester client) {
		
		this.client = client; // reference to specific client
		
		// Create a frame; a window displaying to the user
		frame = new JFrame("BlackJack Game");	
		frame.setSize(1100,700);		       				  // set window size length x width
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make it so the window closes at exit
		
		// Create main panel that will hold the 3 different screens (login, lobby, table)
		mainPanel = new JPanel();					
		mainPanel.setLayout(new CardLayout()); 				  // Use CardLayout layout for switching between multiple views
		
		playersContainer = new DefaultListModel<>();

		/******************************************************************************************/
		/* 				TEMPORARY TEST DATA - REMOVE LATER WHEN SERVER SENDS PLAYER CONNECTION LIST			  */
		playersContainer.addElement("Player 2");
		playersContainer.addElement("Player 3");
		playersContainer.addElement("Player 4");
		playersContainer.addElement("Player 5");
		playersContainer.addElement("Player 6");
		/*******************************************************************************************/
		
		// add screens to the main panel (used for switching between screens)
		loginPanel = createLoginPanel();
		signUpPanel = createSignUpPanel();
		playerlobbyPanel = createPlayerLobbyPanel();
		tableScreenPanel = createPlayerTablePanel();
		//tablePanel = createTablePanel();
		
		
		mainPanel.add(loginPanel, "LOGIN");
		mainPanel.add(playerlobbyPanel, "PLAYER_LOBBY");
		//mainPanel.add(tablePanel, "TABLE");
		mainPanel.add(signUpPanel, "REGISTER");
		mainPanel.add(tableScreenPanel, "PLAYER_TABLE");
		
		// add main panel to frame/window
		frame.add(mainPanel);
		
		//Set visibility to true to display on screen
		frame.setVisible(true);
		
		showLoginScreen(); 								     // starting screen to play game (login screen)
	}
	
	/****************************************************************************************************
	 * 
	 * 										CREATE DIFFERENT SCREENS
	 * 
	 ****************************************************************************************************/
	
	
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
		JPanel loginPanel = new JPanel();
		
		loginPanel.setBackground(new Color(0,80,0));		// set the background of the created panel to a custom color (dark greenish, casino table vibe)
		loginPanel.setBounds(295, 150, 500, 400);		//  set the location + size of panel (x, y, length, width)
	
		
		loginPanel.setLayout(new GridBagLayout());		// create a GridBagLayout for customization
		
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
	    loginPanel.add(userTitle, layoutControl);			// add label to panel
	    
	    //------------Username Text Field--------------
	    JTextField username = new JTextField(15);			// create a text field that has a capacity of 15
	    layoutControl.gridy = 1;							// go under the label ("Username")
	    loginPanel.add(username, layoutControl);			// add text field to panel
	    
	    //---------------------------------------------
	    // 			CREATE PASSWORD FIELDS
	    //---------------------------------------------
	    
	    //------------Password Label-------------------
	    JLabel paswrdTitle = new JLabel("Password");
	    paswrdTitle.setForeground(Color.WHITE);
	    layoutControl.gridy = 2;
	    loginPanel.add(paswrdTitle, layoutControl);
	    
	    //------------Password Text Field--------------
	    JTextField password = new JTextField(15);
	    layoutControl.gridy = 3;
	    loginPanel.add(password, layoutControl);
	    
	    
	    //------------Sign Up Option Label--------------
	    JLabel ifAccount = new JLabel("No Account Yet?!");	   // make label if user hasn't made an account yet
	    ifAccount.setForeground(Color.WHITE);				   // set text color to white
	    ifAccount.setFont(new Font("Arial", Font.ITALIC, 11));
	    layoutControl.gridx = 0;							   // (x, y) <=>(0,0) (from top to bottom, ex:    |_0__|_1__|...
	    layoutControl.gridy = 4;							   //									  	   0  |____|_____
	    layoutControl.gridwidth = 2;						   // width; taking up how many columns	      1   |____|_____		)
	    loginPanel.add(ifAccount, layoutControl);			   // add label to panel
	    
	    //---------------------------------------------
	  	// 			CREATE BUTTONS
	  	//---------------------------------------------
	    
	    //------------Sign Up Button-------------------
	    JButton signUpBtn = new JButton("Sign Up");
	    signUpBtn.setForeground(Color.BLACK);
	    layoutControl.gridx = 0;
	    layoutControl.gridy = 5;
	    layoutControl.gridwidth = 1;
	    loginPanel.add(signUpBtn, layoutControl);
	    
	    //------------Login Button---------------------
	    JButton loginBtn = new JButton("Login");
	    loginBtn.setForeground(Color.BLACK);
	    layoutControl.gridx = 1;
	    layoutControl.gridy = 5;
	    layoutControl.gridwidth = 1;
	    loginPanel.add(loginBtn, layoutControl);
	    
		bckgrd.add(loginPanel);
		
		
		JPanel group = new JPanel(new BorderLayout()); // create a panel to return at end of the method
		group.add(bckgrd);							   // add label to panel
		
		/****************************************************
		 * 					ACTION LISTENERS
		 ****************************************************/
		
		signUpBtn.addActionListener(actionEvent ->{
			String userInput = username.getText();
			String pwInput = password.getText();
			
			client.sendMessage("REGISTER_REQUEST" + " " + userInput + " " + pwInput);  //**********WILL MODIFY ONCE CLIENT IS MADE*************
			showSignUpScreen();
		});
		
		loginBtn.addActionListener(actionEvent ->{
			String userInput = username.getText();
			String pwInput = password.getText();
			
			client.sendMessage("LOGIN_REQUEST" + " " + userInput + " " + pwInput);     //**********WILL MODIFY ONCE CLIENT IS MADE*************
		});
		
		return group;
	}
	
	private JPanel createSignUpPanel()
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
		JPanel signUpPanel = new JPanel();
		
		signUpPanel.setBackground(new Color(0,80,0));   // set the background of the created panel to a custom color (dark greenish, casino table vibe)
		signUpPanel.setBounds(295, 150, 500, 400);		//  set the location + size of panel (x, y, length, width)
	
		
		signUpPanel.setLayout(new GridBagLayout());		// create a GridBagLayout for customization
		
		// the layout control for placing components in a GridBagLayout; 
		// where and how each item is placed
		GridBagConstraints layoutControl = new GridBagConstraints();
		
		layoutControl.insets = new Insets(8,8,8,8); 		// adds padding around components (top, left, bottom, right)
		layoutControl.anchor = GridBagConstraints.CENTER;   // centers the following components
		layoutControl.fill = GridBagConstraints.HORIZONTAL; // fill space horizontal
		//---------------------------------------------
		// 			CREATE ROLE OPTION FIELD
		//---------------------------------------------
		
		//------------Role Label-------------------
		JLabel roleLabel = new JLabel("Sign Up As:");
		roleLabel.setForeground(Color.WHITE);
		
		// Layout Modifiers
		layoutControl.gridx = 0;
	    layoutControl.gridy = 0;
	    layoutControl.gridwidth = 2;
	    signUpPanel.add(roleLabel, layoutControl);
	    
	    //------------Role Dropdown Box-------------------
		String[] roles = {"Player",  "Dealer"};
		JComboBox<String> roleOptions = new JComboBox<>(roles);
		
		// Layout Modifiers
		layoutControl.gridx = 0;
	    layoutControl.gridy = 1;
	    layoutControl.gridwidth = 2;
	    
	    signUpPanel.add(roleOptions, layoutControl);
		
		//---------------------------------------------
		// 			CREATE USERNAME FIELDS
		//---------------------------------------------
		
		//------------Username Label-------------------
	    JLabel userTitle = new JLabel("Username");			// make label
	    userTitle.setForeground(Color.WHITE);				// set text color to white
	    layoutControl.gridx = 0;							// (x, y) <=>(0,0) (from top to bottom, ex:    |_0__|_1__|...
	    layoutControl.gridy = 3;							//									  	   0   |____|_____
	    layoutControl.gridwidth = 2;						// width; taking up how many columns	   1   |____|_____		)
	    signUpPanel.add(userTitle, layoutControl);			// add label to panel
	    
	    //------------Username Text Field--------------
	    JTextField username = new JTextField(15);			// create a text field that has a capacity of 15
	    layoutControl.gridy = 4;							// go under the label ("Username")
	    signUpPanel.add(username, layoutControl);			// add text field to panel
	    
	    //---------------------------------------------
	    // 			CREATE PASSWORD FIELDS
	    //---------------------------------------------
	    
	    //------------Password Label-------------------
	    JLabel paswrdTitle = new JLabel("Password");
	    paswrdTitle.setForeground(Color.WHITE);
	    layoutControl.gridy = 5;
	    signUpPanel.add(paswrdTitle, layoutControl);
	    
	    //------------Password Text Field--------------
	    JTextField password = new JTextField(15);
	    layoutControl.gridy = 6;
	    signUpPanel.add(password, layoutControl);
	    
	    
	    //------------Sign Up Option Label--------------
	    JLabel yesAccount = new JLabel("Have One Already?");	   // make label if user hasn't made an account yet
	    yesAccount.setForeground(Color.WHITE);				   // set text color to white
	    yesAccount.setFont(new Font("Arial", Font.ITALIC, 11));
	    layoutControl.gridx = 1;							   // (x, y) <=>(0,0) (from top to bottom, ex:    |_0__|_1__|...
	    layoutControl.gridy = 7;							   //									  	   0  |____|_____
	    layoutControl.gridwidth = 2;						   // width; taking up how many columns	      1   |____|_____		)
	    signUpPanel.add(yesAccount, layoutControl);			   // add label to panel
	    
	    //---------------------------------------------
	  	// 			CREATE BUTTONS
	  	//---------------------------------------------
	    
	    //------------Sign Up Button-------------------
	    JButton signUpBtn = new JButton("Sign Up");
	    signUpBtn.setForeground(Color.BLACK);
	    layoutControl.gridx = 0;
	    layoutControl.gridy = 8;
	    layoutControl.gridwidth = 1;
	    signUpPanel.add(signUpBtn, layoutControl);
	    
	    //------------Login Button---------------------
	    JButton loginBtn = new JButton("Login");
	    loginBtn.setForeground(Color.BLACK);
	    layoutControl.gridx = 1;
	    layoutControl.gridy = 8;
	    layoutControl.gridwidth = 1;
	    signUpPanel.add(loginBtn, layoutControl);
	    
		bckgrd.add(signUpPanel);
		
		
		JPanel group = new JPanel(new BorderLayout()); // create a panel to return at end of the method
		group.add(bckgrd);							   // add label to panel
		
		/****************************************************
		 * 					ACTION LISTENERS
		 ****************************************************/
		
		signUpBtn.addActionListener(actionEvent ->{
			String userInput = username.getText();
			String pwInput = password.getText();
			String role = ((String) roleOptions.getSelectedItem()).toUpperCase(); // saves role user selected to sign up ex: role = "PLAYER" or "DEALER"
			
			client.sendMessage("REGISTER_RESPONSE" + " " + userInput + " " + pwInput + " " + role);  //**********WILL MODIFY ONCE CLIENT IS MADE*************
			showLoginScreen();
		});
		
		loginBtn.addActionListener(actionEvent ->{
			String userInput = username.getText();
			String pwInput = password.getText();
			
			client.sendMessage("LOGIN_REQUEST" + " " + userInput + " " + pwInput);     //**********WILL MODIFY ONCE CLIENT IS MADE*************
			showLoginScreen();
		});
		
		return group;
	}
	
	private JPanel createPlayerLobbyPanel() 
	{
		/****************************************************
		 * 						DESIGN
		 ****************************************************/
		JPanel lobbyPanel = new JPanel();								// create main lobby screen

		lobbyPanel.setLayout(new GridBagLayout());						// set layout to GridBag for controling layout
		
		lobbyPanel.setBackground(new Color(0, 80, 0));					// set background color to a dark green
		
		GridBagConstraints controlLayout = new GridBagConstraints();    // layout modifiers

		controlLayout.insets = new Insets(5,5,5,5); 					// makes space between margins (outside) (top, left, bottom, right)
		controlLayout.fill = GridBagConstraints.BOTH;					// fill screen both horizontally + vertically
		
		//---------------------------------------------
		// 		CREATE LEFT SIDE OF SCREEN 
		//---------------------------------------------
		
		//------------Game Instructions Label-------------------
		JLabel howToHeader = new JLabel("How To Play");					// create label
		howToHeader.setFont(new Font("Arial", Font.BOLD, 20));			// set the font + size
		howToHeader.setForeground(Color.WHITE);							// set text color

		
		// Layout Modifiers
		controlLayout.gridx = 0;										// how many rows it takes up
	    controlLayout.gridy = 0;										// how many columns it takes up	
	    controlLayout.weightx = 0.10;									// how much space horizontally will take up; distribute space; 10%
	    controlLayout.weighty = 0;										// how much space vertically will take up; 0%

	    lobbyPanel.add(howToHeader, controlLayout);						// add label to main panel
	    
	    //------------Game Instructions Text-------------------
		JTextArea instructions = new JTextArea();
		instructions.setText( 
				"Click on one of the\navailable tables to join ongoing " +
				"game.\nIf Table is full you will not be able to join.\n\n" +
				"Once a player joins the table you will be \nplaced in a waiting" +
				"room until next dealing.\n\n" +
				"Once entered you will be allowed to place \nyour bets and enter the" +
				" current pot. Then your \nhand will be dealt when all bets are collected.\n\n" +
				"Dealer will not hit when their hand total is >= 17\n\nTry your best to get your" +
				" hand total to 21!\n\nPLAYER ACTIONS\nHit: Player is dealt a card.\n\n" +
				"Stand: Player confirms and plays on current hand.\n\nFold: Player forfeits hand and" +
				" bet made for current round."
		);

		// Customize font, size, text color, background color
		instructions.setFont(new Font("Arial", Font.PLAIN, 18));
		instructions.setForeground(Color.BLACK);
		instructions.setBackground(Color.LIGHT_GRAY);
		
		
		instructions.setLineWrap(true);									// auto wrap text at line end
	    instructions.setWrapStyleWord(true);							// wrap at the whole word (not in between words)
	    instructions.setEditable(false);								// won't let users edit text area
	    instructions.setPreferredSize(new Dimension(300,0));			// preferred width of layout

	    // Layout Modifiers
		controlLayout.gridx = 0;
		controlLayout.gridy = 1;
		controlLayout.weightx = 0.10;
		controlLayout.weighty = 1.0;

		lobbyPanel.add(instructions, controlLayout);					// add instructions to main panel

		//---------------------------------------------
	  	// 			CREATE LEFT SIDE BUTTONS
	  	//---------------------------------------------
		
		//------------Logout + Account Buttons-------------------
		JPanel leftButtonsPanel = new JPanel();								// create panel to group buttons together
		 
		// create account button (shows the balance + other options)
		JButton accountBtn = new JButton("Account");
		accountBtn.setForeground(Color.BLACK);

		// create logout button (returns to login screen)
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setForeground(Color.BLACK);

		// add buttons to button group panel
		leftButtonsPanel.add(accountBtn);
		leftButtonsPanel.add(logoutBtn);
		
		// Layout Modifiers
		controlLayout.gridx = 0;
		controlLayout.gridy = 2;
		controlLayout.weightx = 0.10;
		controlLayout.weighty = 0;
		
		lobbyPanel.add(leftButtonsPanel, controlLayout);						// add button panel to main panel
		
		//---------------------------------------------
		// 		CREATE RIGHT SIDE OF SCREEN 
		//---------------------------------------------
		
		//------------Game Tables Label-------------------
		JLabel tableHeader = new JLabel("Game Tables");
		tableHeader.setForeground(Color.WHITE);
		tableHeader.setFont(new Font("Arial", Font.BOLD, 20));
		
		// Layout Modifiers
		controlLayout.gridx = 1;
		controlLayout.gridy = 0;
		controlLayout.weightx = 0.90;
		controlLayout.weighty = 0;
		
		lobbyPanel.add(tableHeader, controlLayout);						// add table header to main panel

		
		//--------List of Game Tables Available----------
		tableContainer = new DefaultListModel<>(); 			 			// create a container that hold the game tables
		tableList = new JList<>(tableContainer);               			// holds the tables data into a list

		tableList.setFont(new Font("Arial", Font.BOLD, 18));
		/******************************************************************************************/
		/* 				TEMPORARY TEST DATA - REMOVE LATER WHEN SERVER SENDS TABLE LIST			  */
		
		tableContainer.addElement("Table 1 (2/5 players)");
		tableContainer.addElement("Table 2 (4/5 players)");
		tableContainer.addElement("Table 3 (1/5 players)");
		
		/*******************************************************************************************/
		
		
		tableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// lets you click 1 table at a time
			     
		JScrollPane scroll = new JScrollPane(tableList); 				// puts list into a scrollable pane (only on the right side)
		    
		// Layout Modifiers
		controlLayout.gridx = 1;
		controlLayout.gridy = 1;
		controlLayout.weightx = 0.90;
		controlLayout.weighty = 1.0;
		
		lobbyPanel.add(scroll, controlLayout);							// add scroll pane to main panel
	    
		//---------------------------------------------
	  	// 			CREATE LEFT SIDE BUTTONS
	  	//---------------------------------------------
		
		//------------Join Table Button-------------------
		JPanel rightButtonsPanel = new JPanel();
		
		JButton joinBtn = new JButton("Join Table");
		joinBtn.setForeground(Color.BLACK);
		
		// Position button panel (BOTTOM RIGHT)
		controlLayout.gridx = 1;      // left column
		controlLayout.gridy = 2;      // bottom row
		controlLayout.weightx = 0.10; // same width as instructions
		controlLayout.weighty = 0;    // no vertical stretching
		
		rightButtonsPanel.add(joinBtn);
		lobbyPanel.add(rightButtonsPanel, controlLayout);
	    /****************************************************
		 * 					ACTION LISTENERS
		 ****************************************************/
		
		// when logout button is clicked it sends user to login screen
	    logoutBtn.addActionListener(actionEvent -> showLoginScreen());
		
	    // when account button is clicked it sends user to account pop up screen
	    accountBtn.addActionListener(actionEvent -> showAccountPopup());
		
	    // when join button is selected 
	    joinBtn.addActionListener(actionEvent -> {
	    	
	    	int index = tableList.getSelectedIndex(); 								// index of selected table from JList
	    	
	    	// if no tables are selected once user clicks the button
	    	if(index == -1)
	    	{
	    		// shows a warning pop up "Select a Table First!"
	    		JOptionPane.showMessageDialog(lobbyPanel, "Select a Table First!");
	    		
	    		return; // stops executing to prevent the invalid join
	    	}
	    	
	    	String table = tableContainer.getElementAt(index);						// convert index to a table string
	    	
	    	// asks user for confirmation of it they want to join table or not
	    	int choice = JOptionPane.showConfirmDialog(
	    			lobbyPanel, 
	    			"Join " + table + "?",
	    			"Join Table",
	    			JOptionPane.YES_NO_OPTION
	    	);
	    	
	    	// if user confirms
	    	if(choice == JOptionPane.YES_OPTION) {
	    		// show what table user is joining
	    		JOptionPane.showMessageDialog(lobbyPanel, "Joining " + table);
	    		
	    		// tells server player wants to join a table
	    		client.sendMessage("JOIN_TABLE_REQUEST"); //**********WILL MODIFY ONCE CLIENT IS MADE*************
	    		
	    		showPlayerTableScreen(); // proceed to going to table game (Player perspective)
	    	}
	    			
	    });
		return lobbyPanel;
	}
	/*
	
	private JPanel createDealerLobbyPanel() 
	{
		
		// return the panel
	}
	private JPanel createDealerTablePanel() 
	{
		
		// return the panel
	}
	*/
	private JPanel createPlayerTablePanel() {

		JPanel tablePanel = new JPanel();

		tablePanel.setLayout(new GridBagLayout());
		tablePanel.setPreferredSize(null);
		tablePanel.setBackground(new Color(0, 80, 0));		

		tablePanel.setPreferredSize(new Dimension(1100,700));
		
		GridBagConstraints layoutControl = new GridBagConstraints();
				
		layoutControl.insets = new Insets(10,10,10,10); 		// adds padding around components (top, left, bottom, right)
		layoutControl.fill = GridBagConstraints.NONE; // fill space horizontal
		layoutControl.anchor = GridBagConstraints.CENTER;
		
		JLabel dealerLbl = new JLabel("Dealer");
	    dealerLbl.setForeground(Color.WHITE);
	    dealerLbl.setFont(new Font("Arial", Font.BOLD, 24));
			
	    layoutControl.gridx = 2;
   		layoutControl.gridy = 0;
   		layoutControl.gridwidth = 1;
   		layoutControl.weightx = .20;
   		layoutControl.weighty = .20;
   		
   		tablePanel.add(dealerLbl,layoutControl);
   		
   		dealerCards = new JPanel();
   		
   		dealerCards.add(createCardImage("ACE_SPADES")); //**********TEMP, reality: client gets -> dealerCard1, dealerCard.add(createCardImage(receivedCard))
   		
   		faceDownCard = createCardImage("back");
   		
   		dealerCards.add(faceDownCard);
   		
   		layoutControl.gridx = 2;
   		layoutControl.gridy = 1;
   		
   		tablePanel.add(dealerCards, layoutControl);
   		   		
   		GridBagConstraints playerLayout = new GridBagConstraints();
   		playerLayout.insets = new Insets(10,10,10,10);
   		playerLayout.fill = GridBagConstraints.NONE;
   		
   		
		// build player boxes
	    for (int i = 0; i < playersContainer.size(); i++) {

	        String name = playersContainer.getElementAt(i);

	        JPanel playerBox = createPlayerBox(name, 0, 0);
	        
	        switch(i)
	        {
	        case 0:
	        	layoutControl.gridx = 0;
	    		layoutControl.gridy = 2;
	    		break;
	        case 1:
	        	layoutControl.gridx = 0;
	    		layoutControl.gridy = 4;
	    		break;
	        case 2:
	        	layoutControl.gridx = 4;
	    		layoutControl.gridy = 1;
	    		break;
	        case 3:
	        	layoutControl.gridx = 4;
	    		layoutControl.gridy = 3;
	    		break;
	        case 4:
	        	layoutControl.gridx = 4;
	    		layoutControl.gridy = 5;
	    		break;
	        } 
	        
	        tablePanel.add(playerBox,layoutControl);
	    }
	    
	    playerCards = new JPanel();
        layoutControl.fill = GridBagConstraints.NONE;

        playerCards.add(createCardImage("ACE_SPADES"));
        playerCards.add(createCardImage("10_HEARTS"));
        
        layoutControl.gridx = 2;
		layoutControl.gridy = 6;
		
        tablePanel.add(playerCards, layoutControl);
        
        
        JPanel actionBtns = new JPanel(new BorderLayout());
        
        JButton hitBtn = new JButton("Hit");
        JButton standBtn = new JButton("Stand");
        JButton foldBtn = new JButton("Fold");
        
        actionBtns.add(hitBtn, BorderLayout.NORTH);
        actionBtns.add(standBtn, BorderLayout.CENTER);
        actionBtns.add(foldBtn, BorderLayout.SOUTH);
        
        layoutControl.gridx = 1;
		layoutControl.gridy = 6;
		
        tablePanel.add(actionBtns, layoutControl);
        
        JButton betBtn = new JButton("Bet");
        
        layoutControl.gridx = 3;
		layoutControl.gridy = 6;
		
        tablePanel.add(betBtn, layoutControl);
        
        JButton exitBtn = new JButton("Leave Table");
        
        layoutControl.gridx = 4;
		layoutControl.gridy = 7;
		
        tablePanel.add(exitBtn, layoutControl);
        

        hitBtn.addActionListener(actionEvent -> {
        	
        	if(!hasBet)
        	{
        		JOptionPane.showMessageDialog(tablePanel, "You must bet first!");
        		return;
        	}
        	
        	client.sendMessage("HIT_REQUEST"); //*************CHANGE WHEN SERVER/CLIENT FINISHED*************** "HIT_REQUEST" + playerId
        	
        	addPlayerCard("5_CLUBS"); //*************CHANGE WHEN SERVER/CLIENT FINISHED*************** 
        	//server would call like "PLAYER_ACTION_REQUEST" or "HIT_REQUEST" 5_HEARTS, 
        	// client call addPlayerCard(receivedCard)
        });
	    standBtn.addActionListener(actionEvent -> {
	    	if(!hasBet)
        	{
        		JOptionPane.showMessageDialog(tablePanel, "You must bet first!");
        		return;
        	}
	    	
	    	client.sendMessage("STAND_REQUEST");
	    	
	    	flipUpDealer(); //TEMPORARY
	    });
	    foldBtn.addActionListener(actionEvent -> {
	    	
	    	if(!hasBet)
        	{
        		JOptionPane.showMessageDialog(tablePanel, "You must bet first!");
        		return;
        	}
	    	
	    	client.sendMessage("FOLD_REQUEST");
	    	
	    	hitBtn.setEnabled(false);
	    	standBtn.setEnabled(false);
	    	foldBtn.setEnabled(false);

	    	JOptionPane.showMessageDialog(tablePanel, "Folded. Must wait for next round to replay..");
	    });
	    betBtn.addActionListener(actionEvent -> {

	        String input = JOptionPane.showInputDialog(tablePanel, "Enter Bet Amount:");

	        try {
	            if (input != null) 
	            {
	                double amount = Double.parseDouble(input);

	                if (amount <= 0) 
	                {
	                    JOptionPane.showMessageDialog(tablePanel, "Bet must be greater than 0.");
	                    
	                    return;
	                }

	                client.sendMessage("BET_REQUEST " + amount);

	                hasBet = true;
	                
	                hitBtn.setEnabled(true);
	                standBtn.setEnabled(true);
	                foldBtn.setEnabled(true);
	                
	                JOptionPane.showMessageDialog(tablePanel, "You bet: $" + amount);
	            }

	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(tablePanel, "Invalid bet amount.");
	        }
	    });
	    
	    exitBtn.addActionListener(actionEvent -> {
	    	client.sendMessage("LOBBY_REQUEST");
	    	showPlayerLobbyScreen();
	    });
	    
	    return tablePanel;
	}
	
	
	
	/****************************************************************************************************
	 * 
	 * 										SHOW DIFFERENT SCREENS
	 * 
	 ****************************************************************************************************/
	public void showLoginScreen() {

		currentScreen = "LOGIN";										// updates the current screen state to login screen
		((CardLayout) mainPanel.getLayout()).show(mainPanel, "LOGIN");  //
	}	
	
	public void showSignUpScreen() {
		currentScreen = "REGISTER";										// updates the current screen state to login screen
		((CardLayout) mainPanel.getLayout()).show(mainPanel, "REGISTER");  //
	}
	
	public void showPlayerLobbyScreen() {
		currentScreen = "PLAYER_LOBBY";										// updates the current screen state to login screen
		((CardLayout) mainPanel.getLayout()).show(mainPanel, "PLAYER_LOBBY");  //
	}
	
	public void showPlayerTableScreen() {
		currentScreen = "PLAYER_TABLE";										// updates the current screen state to login screen
		((CardLayout) mainPanel.getLayout()).show(mainPanel, "PLAYER_TABLE");  //
		
		frame.revalidate();
		frame.repaint();
		
		JOptionPane.showMessageDialog(frame, "Make a Bet To Start The Game");
	}
	public void showDealerLobbyScreen() {
	}
	
	public void showDealerTableScreen() {
		
	}
	
	public void showAccountPopup() {

		currentScreen = "ACCOUNT";					// updates the state to know what screen user is on
		
		//---------------------------------------------
	  	// 			CREATE POP UP WINDOW
	  	//---------------------------------------------
		// Create a pop up window for account
	    JDialog popUpWindow = new JDialog(frame, "ACCOUNT", true);					
	    
	   
	    popUpWindow.setSize(300, 200);			   // set size of window
	    popUpWindow.setLayout(new BorderLayout()); // a layout with no gaps between components

	    
	    JLabel moneyLbl = new JLabel("Balance: ", SwingConstants.CENTER);  // label for balance
	    moneyLbl.setFont(new Font("Arial", Font.BOLD, 16));				   // set font

	    //---------------------------------------------
	  	// 				CREATE BUTTONS
	  	//---------------------------------------------
	    
		//-------Deposit, Withdraw, Close Buttons---------------
	    JButton depositBtn = new JButton("Deposit");
	    JButton cashOutBtn = new JButton("Cashout");
	    JButton closeBtn = new JButton("Close");

	    //-------Group Buttons To 1 Panel---------------
	    JPanel datCashButtons = new JPanel();
	    
	    datCashButtons.add(depositBtn);
	    datCashButtons.add(cashOutBtn);
	    datCashButtons.add(closeBtn);
	    
	    /****************************************************
		 * 					ACTION LISTENERS
		 ****************************************************/
	    depositBtn.addActionListener(actionListener -> {
	    	
	    	// asks user for deposit amount
	    	String input = JOptionPane.showInputDialog(popUpWindow, "Enter Deposit Amount:");
	    	
	    	try {
	    		
	    		// makes sure user didn't click cancel
	    		if (input != null)
	    		{
	    			double amount = Double.parseDouble(input); 					// Converts string to a number
	    			
	    			// sends deposit request to server to update the balance
	    			client.sendMessage("DEPOSIT_REQUEST " + amount);
	    			
	    		}
		       // handles if input is invalid (letters or empty input)
	    	 } catch(Exception error) {
	    		 JOptionPane.showMessageDialog(popUpWindow,  "Invalid Amount.");
	    	 }
	    });

	    cashOutBtn.addActionListener(actionListener -> {
	    	// asks user for cash out amount
	    	String input = JOptionPane.showInputDialog(popUpWindow, "Enter Cashout Amount:");
	    	
	    	try {
	    		// makes sure user didn't cancel
	    		if (input != null)
	    		{
	    			double amount = Double.parseDouble(input);
	    			
	    			// sends cash out request to server to update the balance
	    			client.sendMessage("CASHOUT_REQUEST " + amount);
	    			
	    		}
	    	   // handles if input is invalid (letters or empty input)
	    	 } catch(Exception error) {
	    		 JOptionPane.showMessageDialog(popUpWindow,  "Invalid Amount.");
	    	 }
	    });

	    closeBtn.addActionListener(actionListener -> popUpWindow.dispose());

	    //-------Add Components to Pop Up Window---------------
	    popUpWindow.add(moneyLbl, BorderLayout.CENTER);
	    popUpWindow.add(datCashButtons, BorderLayout.SOUTH);
	    
	    //-------Placement + Visibility of Window---------------
	    popUpWindow.setLocationRelativeTo(null);
	    popUpWindow.setVisible(true);
	}
	
	
	
	/****************************************************************************************************
	 * 
	 * 								UPDATE IN GAME STATUS DISPLAY METHODS
	 * 
	 ****************************************************************************************************/
	
	
	private JPanel createPlayerBox(String name, int score, int bet)
	{
		JPanel playerBox = new JPanel(new GridBagLayout());
		
		playerBox.setBackground(Color.WHITE);
	    playerBox.setPreferredSize(new Dimension(100, 100));
	    
	    GridBagConstraints layoutControl = new GridBagConstraints();
		
	    layoutControl.insets = new Insets(5,10,5,10); 		// adds padding around components (top, left, bottom, right)
	    layoutControl.anchor = GridBagConstraints.CENTER;
	    layoutControl.fill = GridBagConstraints.NONE;
	    
	    JLabel nameLbl = new JLabel(name);
	  
	    layoutControl.gridx = 0;
	    layoutControl.gridy = 0;
	    layoutControl.gridwidth = 2;

	    playerBox.add(nameLbl, layoutControl);
	    
	    JLabel scoreLbl = new JLabel("Score:");
		  
	    layoutControl.gridx = 0;
	    layoutControl.gridy = 1;
	    layoutControl.gridwidth = 1;
	    
	    playerBox.add(scoreLbl, layoutControl);
	    
	    JLabel points = new JLabel(String.valueOf(score));
		  
	    layoutControl.gridx = 1;
	    layoutControl.gridy = 1;
	    
	    playerBox.add(points, layoutControl);
	    
	    JLabel betLbl = new JLabel("Bet:");
		  
	    layoutControl.gridx = 0;
	    layoutControl.gridy = 2;
	    
	    playerBox.add(betLbl, layoutControl);
	    
	    JLabel gameBet = new JLabel(String.valueOf(bet));
		  
	    layoutControl.gridx = 1;
	    layoutControl.gridy = 2;
	    
	    playerBox.add(gameBet, layoutControl);

	    return playerBox;
	}
	
	private JLabel createCardImage(String cardName)
	{
	    String imgPath = "/images/" + cardName + ".png";

	    java.net.URL imgURL = getClass().getResource(imgPath);

	    if (imgURL == null)
	    {
	        System.err.println("CARD IMAGE NOT FOUND: " + imgPath);

	        return new JLabel("?");
	    }

	    ImageIcon icon = new ImageIcon(imgURL);

	    Image scaled = icon.getImage().getScaledInstance(
	        70, 100, Image.SCALE_SMOOTH
	    );

	    return new JLabel(new ImageIcon(scaled));
	}
	
	public void updatePlayers(DefaultListModel<String> newPlayers)
	{
	    playersContainer = newPlayers;

	    SwingUtilities.invokeLater(() -> { // run later on UI thread

	    	 tableScreenPanel.removeAll(); 

	         GridBagConstraints layoutControl = new GridBagConstraints();
	         layoutControl.insets = new Insets(10,10,10,10);
	         
	    	 for (int i = 0; i < playersContainer.size(); i++) {
	    		 String name = playersContainer.getElementAt(i);

	             JPanel playerBox = createPlayerBox(name, 0, 0);

	            switch(i)
	            {
	                case 0 -> { layoutControl.gridx = 0; layoutControl.gridy = 2; }
	                case 1 -> { layoutControl.gridx = 0; layoutControl.gridy = 4; }
	                case 2 -> { layoutControl.gridx = 4; layoutControl.gridy = 1; }
	                case 3 -> { layoutControl.gridx = 4; layoutControl.gridy = 3; }
	                case 4 -> { layoutControl.gridx = 4; layoutControl.gridy = 5; }
	            }

	            tableScreenPanel.add(playerBox, layoutControl);	 
	            
	            }

	        tableScreenPanel.revalidate();
	        tableScreenPanel.repaint();
	    });
	}
	
	public void addPlayerCard(String cardName)
	{
		JLabel card = new JLabel();
		
		card = createCardImage(cardName);
		
		playerCards.add(card);
		
		playerCards.revalidate();
		playerCards.repaint();
	}
	
	public void revealCard(String cardName)
	{
		dealerCards.removeAll();
		
		dealerCards.add(createCardImage("ACE_SPADES"));  
		dealerCards.add(createCardImage(cardName));      
		
		dealerCards.revalidate();
		dealerCards.repaint();
	}
	
	
	public void flipUpDealer()
	{
		dealerCards.removeAll();
		
		// change to something like dealerCard.add(createCardImage(serverCard1 etc))
		// rn for testing
		dealerCards.add(createCardImage("ACE_SPADES"));   
		dealerCards.add(createCardImage("10_HEARTS"));   
		
		dealerCards.revalidate();
		dealerCards.repaint();
		
	}
	
	
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
