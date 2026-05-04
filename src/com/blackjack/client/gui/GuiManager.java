package com.blackjack.client.gui;

//import com.blackjack.testing.FakeClientGUITester; // Dummy CLient to test GUI while Client.java doesn't exist yet
import com.blackjack.client.Client;

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

public class GuiManager {

	private Client client;		 					 													// temporary dummy client, reference to client for GUI to send messages
	
	/***********TEMPORARY LISTS FOR LOBBY + TABLE PLAYERS BEFORE CLIENT/SERVER CLASSES MADE*********
	 * for testing purposes
	 */
	private DefaultListModel<String> playersContainer;
	/***********************************************************************************************/
	
	private String currentScreen;								 													// variable for tracking which screen is currently active for user
	
	private boolean hasBet = false; 							 													// variable for tracking if player placed a bet or not 
	
	private JFrame frame;												 											// main window
	private JPanel mainPanel, 											 											// holds all different screens (login/sign up, lobbies, tables)
		   		   loginPanel, signUpPanel, playerlobbyPanel, dealerLobbyPanel, playerTablePanel, dealerTablePanel, // the individual screens or "panels"
		   		   playerCards, dealerCards;  				 														// player + dealer separate visual cards
	
	private JLabel playerStatusLbl, dealerStatusLbl, balanceLbl, currBetLbl, pHandLbl, dHandLbl, faceDownCard;		// display info labels for game table screens
	
	
	public GuiManager(Client client) {
		
		// reference to client so GUI can send messages
		this.client = client; 
		
		//------------Frame Creation-------------------
		// a window displaying to the user
		frame = new JFrame("BlackJack Game");	
		frame.setSize(1100,700);		       				  // set window size length x width
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make it so the window closes at exit
		
		//------------Create Main Panel-------------------
		// holds the 6 different screens (login, sign up, player/dealer lobby, player/dealer table)
		mainPanel = new JPanel();							  // create panel	
		mainPanel.setLayout(new CardLayout()); 				  // Use CardLayout layout for switching between multiple views
		
		playersContainer = new DefaultListModel<>();

		/******************************************************************************************/
		/* 				TEMPORARY TEST DATA - REMOVE LATER WHEN SERVER SENDS PLAYER CONNECTION LIST			  */
		playersContainer.addElement("Player 1");
		playersContainer.addElement("Player 2");
		playersContainer.addElement("Player 3");
		playersContainer.addElement("Player 4");
		playersContainer.addElement("Player 5");
		playersContainer.addElement("Player 6");
		/*******************************************************************************************/
		
		//------------CREATE different screens to panel-------------------
		loginPanel = createLoginPanel();
		signUpPanel = createSignUpPanel();
		
		playerlobbyPanel = createPlayerLobbyPanel();
		dealerLobbyPanel = createDealerLobbyPanel();
		
		playerTablePanel = createPlayerTablePanel();
		dealerTablePanel = createDealerTablePanel();

		
		//------------add to main panel-------------------
		mainPanel.add(loginPanel, "LOGIN");
		mainPanel.add(signUpPanel, "REGISTER");
		
		mainPanel.add(playerlobbyPanel, "PLAYER_LOBBY");
		mainPanel.add(dealerLobbyPanel, "DEALER_LOBBY");
		
		mainPanel.add(playerTablePanel, "PLAYER_TABLE");
		mainPanel.add(dealerTablePanel, "DEALER_TABLE");
		
		//------------add main panel to frame/window user sees-------------------
		// add main panel to frame/window
		frame.add(mainPanel);
		
		//Set visibility to true to display on screen
		frame.setVisible(true);
		
		// starting screen to play game (login screen)
		showLoginScreen(); 								    
	}
	
	/*===================================================================================================
	 
	  										CREATE DIFFERENT SCREENS
	  
	 ===================================================================================================*/
	
	
	private JPanel createLoginPanel() 
	{
		/*===================================================
 							DESIGN PANEL
		 ====================================================*/
		// create background of current screen
		ImageIcon og = new ImageIcon(getClass().getResource("/images/background.JPEG"));
		 
		// scale the image to fit onto screen in a good proportion visually
		Image fitToSize = og.getImage().getScaledInstance(1100, 700, Image.SCALE_SMOOTH);
		
		// make the image a label
		JLabel bckgrd = new JLabel(new ImageIcon(fitToSize));
		
		// create the panel
		JPanel loginPanel = new JPanel();
		
		loginPanel.setBackground(new Color(0,80,0));		// set the background of the created panel to a custom color (dark greenish, casino table vibe)
		loginPanel.setBounds(295, 150, 500, 400);			//  set the location + size of panel (x, y, length, width)
	
		
		loginPanel.setLayout(new GridBagLayout());			// create a GridBagLayout for customization
		
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
		
		
		JPanel group = new JPanel(new BorderLayout()); 			// create a panel to return at end of the method
		group.add(bckgrd);							   			// add label to panel to have background
		
		/*===================================================
							ACTION LISTENERS
		  ====================================================*/
		
	    //------------Sign Up Button-------------------
		signUpBtn.addActionListener(actionEvent ->{
			// go to sign up panel to input information to register
			showSignUpScreen();
		});
		
	    //------------Login Button---------------------
		loginBtn.addActionListener(actionEvent ->{
			// take in user input
			String userInput = username.getText();
			String pwInput = password.getText();
			
			// send message to client, user wants to sign in with typed in username + password
			client.sendMessage("LOGIN_REQUEST" + " " + userInput + " " + pwInput);     //**********WILL MODIFY ONCE CLIENT IS MADE*************
		});
		
		return group;
	}
	
	private JPanel createSignUpPanel()
	{
		/*===================================================
							DESIGN PANEL
		  ====================================================*/
		// create background of current screen
		ImageIcon og = new ImageIcon(getClass().getResource("/images/background.JPEG"));
		 
		// scale the image to fit onto screen in a good proportion visually
		Image fitToSize = og.getImage().getScaledInstance(1100, 700, Image.SCALE_SMOOTH);
		
		// make the image a label
		JLabel bckgrd = new JLabel(new ImageIcon(fitToSize));
		
		// create the panel
		JPanel signUpPanel = new JPanel();
		
		signUpPanel.setBackground(new Color(0,80,0));   	// set the background of the created panel to a custom color (dark greenish, casino table vibe)
		signUpPanel.setBounds(295, 150, 500, 400);			//  set the location + size of panel (x, y, length, width)
	
		
		signUpPanel.setLayout(new GridBagLayout());			// create a GridBagLayout for customization
		
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
	    
		// Layout Modifiers
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
	    JLabel yesAccount = new JLabel("Have One Already?");   // make label if user hasn't made an account yet
	    yesAccount.setForeground(Color.WHITE);				   // set text color to white
	    yesAccount.setFont(new Font("Arial", Font.ITALIC, 11));
	    
		// Layout Modifiers
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
	    
		// Layout Modifiers
	    layoutControl.gridx = 0;
	    layoutControl.gridy = 8;
	    layoutControl.gridwidth = 1;
	    signUpPanel.add(signUpBtn, layoutControl);
	    
	    //------------Login Button---------------------
	    JButton loginBtn = new JButton("Login");
	    loginBtn.setForeground(Color.BLACK);
	    
		// Layout Modifiers
	    layoutControl.gridx = 1;
	    layoutControl.gridy = 8;
	    layoutControl.gridwidth = 1;
	    signUpPanel.add(loginBtn, layoutControl);
	    
		bckgrd.add(signUpPanel);
		
		JPanel group = new JPanel(new BorderLayout()); // create a panel to return at end of the method
		group.add(bckgrd);							   // add label to panel
		
		/*===================================================
							ACTION LISTENERS
		  ====================================================*/
		
		//------------Sign Up Button-------------------
		signUpBtn.addActionListener(actionEvent ->{
			// get user input
			String userInput = username.getText();
			String pwInput = password.getText();
			String role = ((String) roleOptions.getSelectedItem()).toUpperCase(); // saves role user selected to sign up ex: role = "PLAYER" or "DEALER"
			
			// send message to client, user wants to sign up with typed in username + password + role for deciding different lobby/game table
			client.sendMessage("REGISTER_RESPONSE" + " " + userInput + " " + pwInput + " " + role);  //**********WILL MODIFY ONCE CLIENT IS MADE*************
			
			// show login screen after making account
			showLoginScreen();
		});
		
		//------------Login Button---------------------
		loginBtn.addActionListener(actionEvent ->{
			// go to login screen
			showLoginScreen();
		});
		
		return group;
	}
	
	private JPanel createPlayerLobbyPanel() 
	{
		/*===================================================
							DESIGN PANEL
		  ====================================================*/
		JPanel lobbyPanel = new JPanel();								// create main lobby screen

		lobbyPanel.setLayout(new GridBagLayout());						// set layout to GridBag for customizing layout a certain way
		
		lobbyPanel.setBackground(new Color(0, 80, 0));					// set background color to a dark green
		
		GridBagConstraints controlLayout = new GridBagConstraints();    // for customizing the layout of the panel

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
		JPanel leftButtonsPanel = new JPanel();							// create panel to group buttons together
		 
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
		
		lobbyPanel.add(leftButtonsPanel, controlLayout);				// add button panel to main panel
		
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
		DefaultListModel<String> tableContainer = new DefaultListModel<>(); 	// create a container that hold the game tables
		JList<String> tableList = new JList<>(tableContainer);               	// holds the tables data into a list

		tableList.setFont(new Font("Arial", Font.BOLD, 18));
		/******************************************************************************************/
		/* 				TEMPORARY TEST DATA - REMOVE LATER WHEN SERVER SENDS TABLE LIST			  */
		
		tableContainer.addElement("Table 1 (2/5 players)");
		tableContainer.addElement("Table 2 (4/5 players)");
		tableContainer.addElement("Table 3 (1/5 players)");
		
		/*******************************************************************************************/
		
		tableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// lets you click 1 table at a time, not multiple selections
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
		
		// Layout Modifiers
		controlLayout.gridx = 1;      									// left column
		controlLayout.gridy = 2;      									// bottom row
		controlLayout.weightx = 0.10; 									// same width as instructions
		controlLayout.weighty = 0;    									// no vertical stretching
		
		rightButtonsPanel.add(joinBtn);
		lobbyPanel.add(rightButtonsPanel, controlLayout);
		/*===================================================
							ACTION LISTENERS
		  ====================================================*/
		
		// when logout button is clicked it sends user to login screen
	    logoutBtn.addActionListener(actionEvent -> showLoginScreen());
		
	    // when account button is clicked it sends user to account pop up screen
	    accountBtn.addActionListener(actionEvent -> showAccountPopup());
		
	    // when join button is selected 
	    joinBtn.addActionListener(actionEvent -> {
	    	
	    	// index of selected table from JList
	    	int index = tableList.getSelectedIndex(); 								
	    	
	    	//------------No Tables Selected-------------------
	    	// if no tables are selected once user clicks the button
	    	if(index == -1)
	    	{
	    		// shows a warning pop up "Select a Table First!"
	    		JOptionPane.showMessageDialog(lobbyPanel, "Select a Table First!");
	    		
	    		return; // stops executing to prevent the invalid join
	    	}
	    	
	    	// convert index to a table string
	    	String table = tableContainer.getElementAt(index);						
	    	
	    	// asks user for confirmation of it they want to join table or not
	    	int choice = JOptionPane.showConfirmDialog(
	    			lobbyPanel, 
	    			"Join " + table + "?",
	    			"Join Table",
	    			JOptionPane.YES_NO_OPTION
	    	);
	    	
	    	//------------User Confirms-------------------
	    	// if user confirms
	    	if(choice == JOptionPane.YES_OPTION) {
	    		// show what table user is joining
	    		JOptionPane.showMessageDialog(lobbyPanel, "Joining " + table);
	    		
	    		// tells server player wants to join a table
	    		client.sendMessage("JOIN_TABLE_REQUEST"); //**********WILL MODIFY ONCE CLIENT IS MADE*************
	    		
	    		showPlayerTableScreen(); // proceed to going to player game table
	    	}
	    			
	    });
	    
		return lobbyPanel;
	}
	
	private JPanel createDealerLobbyPanel() 
	{
		
		/*===================================================
							DESIGN PANEL
		  ====================================================*/
		JPanel dLobbyPanel = new JPanel();								// create main lobby screen

		dLobbyPanel.setLayout(new GridBagLayout());						// set layout to GridBag for controling layout
		
		dLobbyPanel.setBackground(new Color(0, 80, 0));					// set background color to a dark green
		
		GridBagConstraints controlLayout = new GridBagConstraints();    // layout modifiers

		controlLayout.insets = new Insets(5,5,5,5); 					// makes space between margins (outside) (top, left, bottom, right)
		controlLayout.fill = GridBagConstraints.BOTH;					// fill screen both horizontally + vertically
		
		//---------------------------------------------
		// 		CREATE LEFT SIDE OF SCREEN 
		//---------------------------------------------
		
		//------------Game Instructions Label-------------------
		JLabel rulesHeader = new JLabel("JOB RULES");					// create label
		rulesHeader.setFont(new Font("Arial", Font.BOLD, 20));			// set the font + size
		rulesHeader.setForeground(Color.WHITE);							// set text color
		
		// Layout Modifiers
		controlLayout.gridx = 0;										// how many rows it takes up
	    controlLayout.gridy = 0;										// how many columns it takes up	
	    controlLayout.weightx = 0.10;									// how much space horizontally will take up; distribute space; 10%
	    controlLayout.weighty = 0;										// how much space vertically will take up; 0%

	    dLobbyPanel.add(rulesHeader, controlLayout);					// add label to main panel
	    
	    //------------Job Rules For Dealer Text-------------------
		JTextArea instructions = new JTextArea();
		instructions.setText( 
				"1. Click on one of the available tables to\n  join a game.\n\n" +
				"2. Start the round to initiate the round.\n\n" +
				"3. Once round ends, replay another\n    round if player(s)" +
				" would like to.\n\n" +
				" - all players must make a bet before\n   they start the round.\n" +
				" - You must hit on 16 or less AND stand \n   on 17+.\n" +
				"\n ENJOY ( ^ v ^ )/"
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

		dLobbyPanel.add(instructions, controlLayout);					// add job rules text to main panel

		//---------------------------------------------
	  	// 			CREATE LEFT SIDE BUTTONS
	  	//---------------------------------------------
		
		//------------Logout Button-------------------
		// create logout button (returns to login screen)
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setForeground(Color.BLACK);

		// Layout Modifiers
		controlLayout.gridx = 0;
		controlLayout.gridy = 2;
		controlLayout.weightx = 0.10;
		controlLayout.weighty = 0;
		
		dLobbyPanel.add(logoutBtn, controlLayout);						// add button to main panel
		
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
		
		dLobbyPanel.add(tableHeader, controlLayout);					// add table header to main panel

		
		//--------List of Game Tables Available----------
		DefaultListModel<String> tableContainer = new DefaultListModel<>(); 			// create a container that hold the game tables
		JList<String> tableList = new JList<>(tableContainer);               			// holds the tables data into a list

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
		
		dLobbyPanel.add(scroll, controlLayout);							// add scroll pane to main panel
	    
		//---------------------------------------------
	  	// 			CREATE LEFT SIDE BUTTONS
	  	//---------------------------------------------
		
		//------------Join Table Button-------------------
		JButton joinBtn = new JButton("Manage Table");
		joinBtn.setForeground(Color.BLACK);
		
		// Position button panel (BOTTOM RIGHT)
		controlLayout.gridx = 1;      // left column
		controlLayout.gridy = 2;      // bottom row
		controlLayout.weightx = 0.10; // same width as instructions
		controlLayout.weighty = 0;    // no vertical stretching

		dLobbyPanel.add(joinBtn, controlLayout);
		/*===================================================
							DESIGN PANEL
		  ====================================================*/
		
		// when logout button is clicked it sends user to login screen
	    logoutBtn.addActionListener(actionEvent -> showLoginScreen());
		
	    // when join button is selected 
	    joinBtn.addActionListener(actionEvent -> {
	    	
	    	int index = tableList.getSelectedIndex(); 								// index of selected table from JList
	    	
			//------------No Selected Table-------------------
	    	// if no tables are selected once user clicks the button
	    	if(index == -1)
	    	{
	    		// shows a warning pop up "Select a Table First!"
	    		JOptionPane.showMessageDialog(dLobbyPanel, "Select a Table First!");
	    		
	    		return; // stops executing to prevent the invalid join
	    	}
	    	
	    	String table = tableContainer.getElementAt(index);						// convert index to a table string
	    	
			//------------User Confirmation-------------------
	    	// asks user for confirmation of it they want to join table or not
	    	int choice = JOptionPane.showConfirmDialog(
	    			dLobbyPanel, 
	    			"Manage " + table + "?",
	    			"Manage Table",
	    			JOptionPane.YES_NO_OPTION
	    	);
	    	
			//------------User Want To Join Table-------------------

	    	// if user confirms
	    	if(choice == JOptionPane.YES_OPTION) {
	    		// show what table user is joining
	    		JOptionPane.showMessageDialog(dLobbyPanel, "Joining " + table);
	    		
	    		// tells server player wants to join a table
	    		client.sendMessage("JOIN_TABLE_REQUEST"); //**********WILL MODIFY ONCE CLIENT IS MADE*************
	    		
	    		showDealerTableScreen(); // proceed to going to dealer game table screen
	    	}
	    			
	    });
	    
		return dLobbyPanel;
	}


	private JPanel createDealerTablePanel() 
	{
		JPanel dealerTablePanel = new JPanel();						// create main dealer table panel

		/*===================================================
						DEALER STATUS LABELS
		  ====================================================*/
		
		//------------Game State Status-------------------
		dealerStatusLbl = new JLabel("Waiting to start round...");
		dealerStatusLbl.setForeground(Color.WHITE);
		dealerStatusLbl.setFont(new Font("Arial", Font.BOLD, 14));

		//------------Score of Hand Total-------------------
		dHandLbl = new JLabel("Dealer Total: 0");
		dHandLbl.setForeground(Color.WHITE);
		
		/*===================================================
								DESIGN
		  ===================================================*/

		dealerTablePanel.setLayout(new GridBagLayout());			// set layout to GridBagLayout for customizing position on panel
		dealerTablePanel.setPreferredSize(new Dimension(1100,700)); // set size of window
		dealerTablePanel.setBackground(new Color(0, 80, 0));	    // set background color to a green
	
		GridBagConstraints layoutControl = new GridBagConstraints();// for modifying layout
				
		layoutControl.insets = new Insets(10,10,10,10); 			// adds padding around components (top, left, bottom, right)
		layoutControl.fill = GridBagConstraints.NONE; 				// no stretching components automatically
		layoutControl.anchor = GridBagConstraints.CENTER;			// centers components in the grid box
   		
   		dealerCards = new JPanel();									// holds dealer's card images
   		
   		/************************************TESTING PURPOSE******************************/
   		
   		dealerCards.add(createCardImage("ACE_SPADES")); //**********TEMP, reality: client gets -> dealerCard1, dealerCard.add(createCardImage(receivedCard))
   		
   		/*********************************************************************************/
   		faceDownCard = createCardImage("back");						// assign picture of a faced down card to variable
   		
   		dealerCards.add(faceDownCard);								// add to dealer card panel
   		
   		// Layout Modifiers
   		layoutControl.gridx = 2;
   		layoutControl.gridy = 6;
   		
   		dealerTablePanel.add(dealerCards, layoutControl);			// add to game table panel
		
		//---------------------------------------------
		// 		CREATE OTHER PLAYERS BOXES
		//---------------------------------------------	

	    for (int i = 0; i < playersContainer.size(); i++) {

	        String name = playersContainer.getElementAt(i);

	        JPanel playerBox = createPlayerBox(name, 0, 0);
	        
	        switch(i)
	        {
	        // Player 1 Position on Screen
	        case 0:
	        	layoutControl.gridx = 0;
	    		layoutControl.gridy = 5;
	    		break;
		    // Player 2 Position on Screen	
	        case 1:
	        	layoutControl.gridx = 0;
	    		layoutControl.gridy = 4;
	    		break;
		    // Player 3 Position on Screen
	        case 2:
	        	layoutControl.gridx = 1;
	    		layoutControl.gridy = 0;
	    		break;
		    // Player 4 Position on Screen
	        case 3:
	        	layoutControl.gridx = 3;
	    		layoutControl.gridy = 0;
	    		break;
			// Player 5 Position on Screen
	        case 4:
	        	layoutControl.gridx = 4;
	    		layoutControl.gridy = 4;
	    		break;
			// Player 6 Position on Screen
	        case 5:
	        	layoutControl.gridx = 4;
	    		layoutControl.gridy = 5;
	        	
	        } 
	        
	        // add player to the table (list of players who join the game 1-6)
	        dealerTablePanel.add(playerBox,layoutControl);
	    }
	    
		//---------------------------------------------
		// 				CREATE BUTTONS
		//---------------------------------------------	
	    
		//------------Start Button-------------------
        JButton startBtn = new JButton("Start Round");
       
        //Layout Modifiers
        layoutControl.gridx = 1;
		layoutControl.gridy = 6;
		
		dealerTablePanel.add(startBtn, layoutControl);
        
		//------------Replay Round Button-------------------
        JButton replayBtn = new JButton("Next Round");
        
        //Layout Modifiers
        layoutControl.gridx = 3;
		layoutControl.gridy = 6;
		
		dealerTablePanel.add(replayBtn, layoutControl);
		replayBtn.setEnabled(false);

		//------------Exit Table Button-------------------
        JButton exitBtn = new JButton("Leave Table");
        
        //Layout Modifiers
        layoutControl.gridx = 4;
		layoutControl.gridy = 7;
		
		dealerTablePanel.add(exitBtn, layoutControl);
        
		/*===================================================
							ACTION LISTENERS
		  ===================================================*/
		
		//------------Start Button-------------------
		startBtn.addActionListener(actionEvent -> {
			replayBtn.setEnabled(true);							// enable replay button because just started round
			startBtn.setEnabled(false);							// disable start button because activated already + avoid multiple clicking button
			
	    	displayDealerStatus("Starting round..");
	    	
	    	// send message to server dealer is starting round
	    	client.sendMessage("START_ROUND_REQUEST");
	    
	    	
	    });
	    
		//------------Replay Round Button-------------------
	    replayBtn.addActionListener(actionEvent -> {
			startBtn.setEnabled(true);							// enable start button to start the next round
			
	    	displayDealerStatus("Starting next round..");
	    	
	    	// send message to server dealer is replaying round
	    	client.sendMessage("REPLAY_REQUEST");
	    });
	    
		//------------Exit Table Button-------------------
	    exitBtn.addActionListener(actionEvent -> {
	    	
	    	// send message to server dealer is wanting to go back to leave table 
	    	client.sendMessage("DEALER_LOBBY_REQUEST");
	    	
	    	showDealerLobbyScreen();
	    });

		/*===================================================
					LAYOUT MODIFY STATUS LABELS
		  ===================================================*/
	    GridBagConstraints infoLayout = new GridBagConstraints();
	    infoLayout.insets = new Insets(5,5,5,5);

		//------------Game State Status-------------------
	    infoLayout.gridx = 2;
	    infoLayout.gridy = 7;
	    dealerTablePanel.add(dealerStatusLbl, infoLayout);

		//------------Score of Hand Total-------------------
	    infoLayout.gridx = 3;
	    dealerTablePanel.add(dHandLbl, infoLayout);
	    
	    return dealerTablePanel;
	    
	}

	private JPanel createPlayerTablePanel() {

		JPanel playerTablePanel = new JPanel();								// create main panel

		/*===================================================
						PLAYER STATUS LABELS
		  ====================================================*/

		//------------Player Status Label-------------------
		playerStatusLbl = new JLabel("Welcome #Blackjack Win Dat Cash");
		playerStatusLbl.setForeground(Color.WHITE);
		playerStatusLbl.setFont(new Font("Arial", Font.BOLD, 14));

		//------------Balance Status Label-------------------
		balanceLbl = new JLabel("Balance: $0.00");
		balanceLbl.setForeground(Color.WHITE);

		//------------Current Bet Status Label-------------------
		currBetLbl = new JLabel("Current Bet: $0.00");
		currBetLbl.setForeground(Color.WHITE);

		//------------Your Score Status Label-------------------
		pHandLbl = new JLabel("Your Total: 0");
		pHandLbl.setForeground(Color.WHITE);

		//------------Dealer Score Total Status Label-------------------
		dHandLbl = new JLabel("Dealer Total: ?");
		dHandLbl.setForeground(Color.WHITE);

		/*===================================================
								DESIGN
		  ====================================================*/
		playerTablePanel.setLayout(new GridBagLayout());
		playerTablePanel.setPreferredSize(new Dimension(1100,700));
		playerTablePanel.setBackground(new Color(0, 80, 0));		

		GridBagConstraints layoutControl = new GridBagConstraints();		// for modifying layout of screen
				
		layoutControl.insets = new Insets(10,10,10,10); 					// adds padding around components (top, left, bottom, right)
		layoutControl.fill = GridBagConstraints.NONE; 						// no automatic stretching of components
		layoutControl.anchor = GridBagConstraints.CENTER;					// center components
		
		//------------Dealer Label-------------------
		JLabel dealerLbl = new JLabel("Dealer");
	    dealerLbl.setForeground(Color.WHITE);
	    dealerLbl.setFont(new Font("Arial", Font.BOLD, 24));
			
	    // Layout Modifiers
	    layoutControl.gridx = 2;
   		layoutControl.gridy = 0;
   		layoutControl.gridwidth = 1;
   		layoutControl.weightx = .20;
   		layoutControl.weighty = .20;
   		
   		playerTablePanel.add(dealerLbl,layoutControl);
   		
		//------------Dealer Card Visuals-------------------
   		dealerCards = new JPanel();
   		
   		//*****************************************************************
   		
   		dealerCards.add(createCardImage("ACE_SPADES")); //**********TEMP, reality: client gets -> dealerCard1, dealerCard.add(createCardImage(receivedCard))
   		
   		//*****************************************************************
   		faceDownCard = createCardImage("back");								// card is faced down visually
   		
   		dealerCards.add(faceDownCard);
   		
   		// Layout Modifiers
   		layoutControl.gridx = 2;
   		layoutControl.gridy = 1;
   		
   		playerTablePanel.add(dealerCards, layoutControl);
   		   		
		//------------Layout Modifier for Player Containers-------------------
   		GridBagConstraints playerLayout = new GridBagConstraints();
   		playerLayout.insets = new Insets(10,10,10,10);
   		playerLayout.fill = GridBagConstraints.NONE;
   		
		//---------------------------------------------
		// 		CREATE OTHER PLAYERS BOXES
		//---------------------------------------------	

	    for (int i = 0; i < playersContainer.size(); i++) {

	        String name = playersContainer.getElementAt(i);					// get names of each player in list sent from client/server

	        JPanel playerBox = createPlayerBox(name, 0, 0);					// create the box other player's info for the game (score, bet)
	        
	        // Position the players on the screen (5 other players max)
	        switch(i)
	        {
	        case 0:
	        	playerLayout.gridx = 0;
	        	playerLayout.gridy = 2;
	    		break;
	        case 1:
	        	playerLayout.gridx = 0;
	        	playerLayout.gridy = 4;
	    		break;
	        case 2:
	        	playerLayout.gridx = 4;
	        	playerLayout.gridy = 1;
	    		break;
	        case 3:
	        	playerLayout.gridx = 4;
	        	playerLayout.gridy = 3;
	    		break;
	        case 4:
	        	playerLayout.gridx = 4;
	        	playerLayout.gridy = 5;
	    		break;
	        } 
	        
	        // add to game table panel
	        playerTablePanel.add(playerBox,playerLayout);
	    }
	    
		//------------Player's cards; Hand-------------------
	    playerCards = new JPanel();
        layoutControl.fill = GridBagConstraints.NONE;

        //********************TEMPORARY TILL CLIENT/SERVER MADE*****
        
        playerCards.add(createCardImage("ACE_SPADES"));
        playerCards.add(createCardImage("10_HEARTS"));
        
        //**********************************************************
        // Layout Modifiers
        layoutControl.gridx = 2;
		layoutControl.gridy = 6;
		
		playerTablePanel.add(playerCards, layoutControl);
        
		/*===================================================
							CREATE BUTTONS
		  ===================================================*/
		//------------Panel that Holds Action Buttons-------------------
        JPanel actionBtns = new JPanel(new BorderLayout());
        
		//------------Hit, Stand, Fold Buttons-------------------
        JButton hitBtn = new JButton("Hit");
        JButton standBtn = new JButton("Stand");
        JButton foldBtn = new JButton("Fold");
        
        // add to action buttons panel
        actionBtns.add(hitBtn, BorderLayout.NORTH);
        actionBtns.add(standBtn, BorderLayout.CENTER);
        actionBtns.add(foldBtn, BorderLayout.SOUTH);
        
        // Layout Modifiers
        layoutControl.gridx = 1;
		layoutControl.gridy = 6;
		
		playerTablePanel.add(actionBtns, layoutControl);
        
		//------------Bet Button-------------------
        JButton betBtn = new JButton("Bet");
        
        // Layout Modifiers
        layoutControl.gridx = 3;
		layoutControl.gridy = 6;
		
		playerTablePanel.add(betBtn, layoutControl);
        
		//------------Leave Table Button-------------------
        JButton exitBtn = new JButton("Leave Table");
        
        // Layout Modifiers
        layoutControl.gridx = 4;
		layoutControl.gridy = 7;
		
		playerTablePanel.add(exitBtn, layoutControl);
        
		/*===================================================
							ACTION LISTENERS
		  ===================================================*/
		//------------Hit Button-------------------
        hitBtn.addActionListener(actionEvent -> {
        	
        	// if no bet, notify you need to make one first before you can play
        	if(!hasBet)
        	{
        		JOptionPane.showMessageDialog(playerTablePanel, "You must bet first!");
        		
        		return;
        	}
        	
        	//*********************************************************
        	
	    	// send message that player wants to hit to server
        	client.sendMessage("HIT_REQUEST"); //*************CHANGE WHEN SERVER/CLIENT FINISHED*************** "HIT_REQUEST" + playerId
        	
        	addPlayerCard("5_CLUBS"); //*************CHANGE WHEN SERVER/CLIENT FINISHED*************** 
        	//server would call like "PLAYER_ACTION_REQUEST" or "HIT_REQUEST" 5_HEARTS, 
        	// client call addPlayerCard(receivedCard)
        	
        	//*********************************************************
        });
        
		//------------Stand Button-------------------
	    standBtn.addActionListener(actionEvent -> {
	    	
        	// if no bet, notify you need to make one first before you can play
	    	if(!hasBet)
        	{
        		JOptionPane.showMessageDialog(playerTablePanel, "You must bet first!");
        		return;
        	}
	    	
	    	// send message that player wants to stand to server
	    	client.sendMessage("STAND_REQUEST");
	    	
	    	// flip up dealer card 
	    	flipUpDealer(); //TEMPORARY
	    });
	    
		//------------Fold Button-------------------
	    foldBtn.addActionListener(actionEvent -> {
	    	
        	// if no bet, notify you need to make one first before you can play
	    	if(!hasBet)
        	{
        		JOptionPane.showMessageDialog(playerTablePanel, "You must bet first!");
        		return;
        	}
	    	
	    	// send message that player wants to fold to server
	    	client.sendMessage("FOLD_REQUEST");
	    	
	    	// disable buttons because fold is to forfeit hand and bet for current round
	    	hitBtn.setEnabled(false);
	    	standBtn.setEnabled(false);
	    	foldBtn.setEnabled(false);

	    	JOptionPane.showMessageDialog(playerTablePanel, "Folded. Must wait for next round to replay..");
	    });
	    
		//------------Bet Button-------------------
	    betBtn.addActionListener(actionEvent -> {

	    	// have user enter bet they want to make
	        String input = JOptionPane.showInputDialog(playerTablePanel, "Enter Bet Amount:");

	        try {
	            if (input != null) 
	            {
	            	// create a $0.00 basically
	                double amount = Double.parseDouble(input);

	                // validate input
	                if (amount <= 0) 
	                {
	                    JOptionPane.showMessageDialog(playerTablePanel, "Bet has to be greater than 0");
	                    
	                    return;
	                }

	                // sends a message to server a bet is being made with the amount user entered
	                client.sendMessage("PLACE_BET_REQUEST " + amount);

	                hasBet = true;		// makes sure bet is made
	                
	                // updates the bet status and displays it (through client/server)
	                displayCurrentBet(amount);
	                
	                // enables all the buttons because a bet was made
	                hitBtn.setEnabled(true);
	                standBtn.setEnabled(true);
	                foldBtn.setEnabled(true);
	                
	                JOptionPane.showMessageDialog(playerTablePanel, "You bet: $" + amount);
	            }

	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(playerTablePanel, "Invalid bet amount.");
	        }
	    });
	    
		//------------Leave Table Button-------------------
	    exitBtn.addActionListener(actionEvent -> {
	    	// return to player lobby
	    	client.sendMessage("LOBBY_REQUEST");
	    	showPlayerLobbyScreen();
	    });
	    
		/*===================================================
					LAYOUT MODIFY STATUS LABELS
		  ===================================================*/
	    GridBagConstraints infoLayout = new GridBagConstraints();
	    infoLayout.insets = new Insets(5,5,5,5);
	    infoLayout.fill = GridBagConstraints.NONE;

		//------------Player Status Label-------------------
	    infoLayout.gridx = 0;
	    infoLayout.gridy = 7;
	    playerTablePanel.add(playerStatusLbl, infoLayout);

		//------------Player Hand Total Label-------------------
	    infoLayout.gridx = 1;
	    playerTablePanel.add(pHandLbl, infoLayout);

		//------------Dealer Hand Total Label-------------------
	    infoLayout.gridx = 2;
	    playerTablePanel.add(dHandLbl, infoLayout);

		//------------Balance Status Label-------------------
	    infoLayout.gridx = 3;
	    playerTablePanel.add(balanceLbl, infoLayout);

		//------------Current Bet Status Label-------------------
	    infoLayout.gridx = 4;
	    playerTablePanel.add(currBetLbl, infoLayout);

	    
	    return playerTablePanel;
	}
	
	
	
	/****************************************************************************************************
	 * 
	 * 										SHOW DIFFERENT SCREENS
	 * 
	 ****************************************************************************************************/
	public void showLoginScreen() {

		currentScreen = "LOGIN";													// updates the current screen state to login screen
		((CardLayout) mainPanel.getLayout()).show(mainPanel, "LOGIN");  			// using CardLayout switch to LOGIN Panel in main Panel
	}	
	
	public void showSignUpScreen() {
		currentScreen = "REGISTER";													// updates the current screen state to login screen
		((CardLayout) mainPanel.getLayout()).show(mainPanel, "REGISTER");  			// using CardLayout switch to REGISTER Panel in main Panel
	}
	
	public void showPlayerLobbyScreen() {
		currentScreen = "PLAYER_LOBBY";												// updates the current screen state to login screen
		((CardLayout) mainPanel.getLayout()).show(mainPanel, "PLAYER_LOBBY");  		// using CardLayout switch to PLAYER_LOBBY Panel in main Panel
	}
	
	public void showPlayerTableScreen() {
		currentScreen = "PLAYER_TABLE";												// updates the current screen state to login screen
		((CardLayout) mainPanel.getLayout()).show(mainPanel, "PLAYER_TABLE");  		// using CardLayout switch to PLAYER_TABLE Panel in main Panel
		
		// refresh the layout to update it with the information changes
		frame.revalidate();
		frame.repaint();
		
		// Pop Up messages to guide the user
		JOptionPane.showMessageDialog(frame, "Wait For Dealer to Start The Game");
		JOptionPane.showMessageDialog(frame, "Make a Bet To Start The Game");
	}
	public void showDealerLobbyScreen() {
		currentScreen = "DEALER_LOBBY";										   		// updates the current screen state to login screen
		((CardLayout) mainPanel.getLayout()).show(mainPanel, "DEALER_LOBBY");  		// using CardLayout switch to DEALER_LOBBY Panel in main Panel
	}
	
	public void showDealerTableScreen() {
		currentScreen = "DEALER_TABLE";												// updates the current screen state to login screen
		((CardLayout) mainPanel.getLayout()).show(mainPanel, "DEALER_TABLE");  		// using CardLayout switch to DEALER_TABLE Panel in main Panel
		
		// refresh the layout to update it with the information changes
		frame.revalidate();
		frame.repaint();
		
		// Pop Up messages to guide the user
		JOptionPane.showMessageDialog(frame, "Start The Game For The Players");
	}
	
	/****************************************************************************************************
	 * 										POP UP WINDOW
	 ****************************************************************************************************/
	public void showAccountPopup() {

		currentScreen = "ACCOUNT";					// updates the state to know what screen user is on
		
		//---------------------------------------------
	  	// 			CREATE POP UP WINDOW
	  	//---------------------------------------------
		// Create a pop up window for account
	    JDialog popUpWindow = new JDialog(frame, "ACCOUNT", true);					
	    
	   
	    popUpWindow.setSize(300, 200);			   							// set size of window
	    popUpWindow.setLayout(new BorderLayout());							 // a layout with no gaps between components

	    
	    JLabel moneyLbl = new JLabel("Balance: ", SwingConstants.CENTER);  	// label for balance
	    moneyLbl.setFont(new Font("Arial", Font.BOLD, 16));				   	// set font

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
	    
	    /*====================================================
		 * 					ACTION LISTENERS
		 ====================================================*/
	    //-------Deposit Button---------------
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

	    //-------Cash Out Button---------------
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

	    //-------Exit Button---------------
	    closeBtn.addActionListener(actionListener -> popUpWindow.dispose());

	    //-------Add Components to Pop Up Window---------------
	    popUpWindow.add(moneyLbl, BorderLayout.CENTER);
	    popUpWindow.add(datCashButtons, BorderLayout.SOUTH);
	    
	    //-------Placement + Visibility of Window---------------
	    popUpWindow.setLocationRelativeTo(null);
	    popUpWindow.setVisible(true);
	}
	
	
	
	/****************************************************************************************************
	 * 								CREATE PLAYER BOX
	 * 							 (player name, score, bet)
	 ****************************************************************************************************/
	JPanel createPlayerBox(String name, int score, int bet)
	{
		JPanel playerBox = new JPanel(); 
		playerBox.setLayout(new GridBagLayout());
		
	    /*====================================================
		 * 					ACTION LISTENERS
		 ====================================================*/
		playerBox.setBackground(Color.WHITE);
	    playerBox.setPreferredSize(new Dimension(100, 100));
	    
	    // Layout Modifier
	    GridBagConstraints layoutControl = new GridBagConstraints();
		
	    layoutControl.insets = new Insets(5,10,5,10); 		// adds padding around components (top, left, bottom, right)
	    layoutControl.anchor = GridBagConstraints.CENTER;
	    layoutControl.fill = GridBagConstraints.NONE;
	    
	    //-------Name Label---------------
	    JLabel nameLbl = new JLabel(name);
	  
	    // Layout Modifiers
	    layoutControl.gridx = 0;
	    layoutControl.gridy = 0;
	    layoutControl.gridwidth = 2;

	    playerBox.add(nameLbl, layoutControl);
	    
	    //-------Score Label---------------
	    JLabel scoreLbl = new JLabel("Score:");
		  
	    // Layout Modifiers
	    layoutControl.gridx = 0;
	    layoutControl.gridy = 1;
	    layoutControl.gridwidth = 1;
	    
	    playerBox.add(scoreLbl, layoutControl);
	    
	    //-------Score Value Label---------------
	    JLabel points = new JLabel(String.valueOf(score));
		  
	    // Layout Modifiers
	    layoutControl.gridx = 1;
	    layoutControl.gridy = 1;
	    
	    playerBox.add(points, layoutControl);
	    
	    //-------Bet Label---------------
	    JLabel betLbl = new JLabel("Bet:");
		  
	    // Layout Modifiers
	    layoutControl.gridx = 0;
	    layoutControl.gridy = 2;
	    
	    playerBox.add(betLbl, layoutControl);
	    
	    //-------Bet Value Label---------------
	    JLabel gameBet = new JLabel(String.valueOf(bet));
		  
	    // Layout Modifiers
	    layoutControl.gridx = 1;
	    layoutControl.gridy = 2;
	    
	    playerBox.add(gameBet, layoutControl);

	    return playerBox;
	}
	

	/****************************************************************************************************
	 * 								CREATE IMAGES IN GAME
	 ****************************************************************************************************/
	private JLabel createCardImage(String cardName)
	{
		// assign file path to variable
	    String imgPath = "/images/" + cardName + ".png";

	    // load images from the file path
	    java.net.URL imgURL = getClass().getResource(imgPath);

	    // if there is no image found, notify
	    if (imgURL == null)
	    {
	        System.err.println("CARD IMAGE NOT FOUND: " + imgPath);

	        return new JLabel("?");
	    }

	    // load image (card)
	    ImageIcon icon = new ImageIcon(imgURL);

	    // resize the image (card)
	    Image scaled = icon.getImage().getScaledInstance(70, 100, Image.SCALE_SMOOTH);

	    // return image
	    return new JLabel(new ImageIcon(scaled));
	}
	
	/****************************************************************************************************
	 * 								REBUILD PLAYER TABLE
	 ****************************************************************************************************/
	public void updatePlayers(DefaultListModel<String> newPlayers)
	{
	    playersContainer = newPlayers;												// update the players list

	    playerTablePanel.removeAll(); 												// remove the old UI

        GridBagConstraints layoutControl = new GridBagConstraints();
        layoutControl.insets = new Insets(10,10,10,10);
        
        // loop through the players list
        for (int i = 0; i < playersContainer.size(); i++) 
        {
   		 String name = playersContainer.getElementAt(i);

         JPanel playerBox = createPlayerBox(name, 0, 0);

         switch(i)
         {
         	case 0:
         	{ 
         		layoutControl.gridx = 0; 
         		layoutControl.gridy = 2; 
         		break;
         	}
            case 1:
            { 
            	layoutControl.gridx = 0; 
            	layoutControl.gridy = 4; 
            	break;
            }
            case 2:
            { 
            	layoutControl.gridx = 4;
            	layoutControl.gridy = 1; 
            	break;
            }
            case 3: 
            { 
            	layoutControl.gridx = 4; 
            	layoutControl.gridy = 3; 
            	break;
            }
            case 4:
            { 
            	layoutControl.gridx = 4; 
            	layoutControl.gridy = 5; 
            	break;
            }
         }

           playerTablePanel.add(playerBox, layoutControl);	 
           
           }

       // refresh the panels
       playerTablePanel.revalidate();
       playerTablePanel.repaint();
	}
	
	/****************************************************************************************************
	 * 								ADD NEW CARD
	 ****************************************************************************************************/
	public void addPlayerCard(String cardName)
	{
		JLabel card = new JLabel();			// label that holds card image
		
		card = createCardImage(cardName);	// replace empty label with card image
		
		playerCards.add(card);				// add card to players card panel (player's hand)
		
		// refresh panel/layout to include new card
		playerCards.revalidate();
		playerCards.repaint();
	}
	/****************************************************************************************************
	 * 							REVEAL DEALER'S FLIPPED DOWN CARD
	 ****************************************************************************************************/
	public void revealCard(String cardName)
	{
		dealerCards.removeAll();						// remove current cards from dealer's panel
		
		dealerCards.add(createCardImage("ACE_SPADES")); // add dealer's first card *************FOR TESTING*********, server sends actual card
		dealerCards.add(createCardImage(cardName));     // add revealed 2nd card
		
		// refresh/update layout/panel
		dealerCards.revalidate();
		dealerCards.repaint();
	}
	
	/****************************************************************************************************
	 * 								FACE UP CARD
	 ****************************************************************************************************/
	public void flipUpDealer()
	{
		dealerCards.removeAll();						// remove face down card version of panel
		
		// change to something like dealerCard.add(createCardImage(serverCard1 etc))
		// rn for testing
		dealerCards.add(createCardImage("ACE_SPADES"));   
		dealerCards.add(createCardImage("10_HEARTS"));   
		
		// refresh/update layout/panel
		dealerCards.revalidate();
		dealerCards.repaint();
		
	}
	
	/****************************************************************************************************
	 * 
	 * 								UPDATE IN GAME STATUS DISPLAY METHODS
	 * 
	 ****************************************************************************************************/
	
	public void displayPlayerStatus(String message) {
		
	    if (playerStatusLbl != null) 
	    {
	    	// send label to new status message
	        playerStatusLbl.setText(message);
	    }
	}
	
	public void displayDealerStatus(String message) {
	    if (dealerStatusLbl != null) 
	    {
	    	// send label to new status message
	        dealerStatusLbl.setText(message);
	    }
	}
	
	public void displayBalance(double amount) {
		if (balanceLbl != null) 
        {
			// format to money format $##.##
            balanceLbl.setText("Balance: $" + String.format("%.2f", amount));
        }
	}
	
	public void displayCurrentBet(double amount) {
		if (currBetLbl != null)
        {
			// format bet amount
            currBetLbl.setText("Current Bet: $" + String.format("%.2f", amount));
        }
	}
	
	public void displayPlayerHand(String cards, int total) {
		
		playerCards.removeAll();							// remove current player card display

        String[] cardArray = cards.split(",");				// split string (ex: "ACE_SPADES,10_HEARTS" to ["ACE_SPADES" , "10_HEARTS"]
        	
        // for each card in the array
        for (String card : cardArray) 
        {
        	// remove the extra spaces + create card image + add to panel
            playerCards.add(createCardImage(card.trim()));
        }

        // refresh panel
        playerCards.revalidate();
        playerCards.repaint();

        // update player's total hand value label
        pHandLbl.setText("Your Total: " + total);
	}
	
	public void displayDealerHand(String cards, int total) {
		 dealerCards.removeAll();
		 String[] cardArray = cards.split(",");

	        for (String card : cardArray) 
	        {
	            dealerCards.add(createCardImage(card.trim()));
	        }

	        dealerCards.revalidate();
	        dealerCards.repaint();

	        dHandLbl.setText("Dealer Showing: " + total);

	}
	
	public void displayRoundResult(String result) {
		displayPlayerStatus(result);			// update player status label
		displayDealerStatus("Round complete");  // set dealer status to complete round

		// Displays result
        JOptionPane.showMessageDialog(
            frame,
            result,
            "ROUND RESULT",
            JOptionPane.INFORMATION_MESSAGE
        );

        hasBet = false; // reset round state
	}
	
}
