package com.blackjack.testing;

// Temporary GUI test class to run and debug screens without backend/client
import com.blackjack.client.gui.GuiManager;

public class GUITest {
	
	public static void main(String[] args)
	{

		// use dummy client for testing
	    FakeClientGUITester testClient = new FakeClientGUITester();
		
		GuiManager gui = new GuiManager(testClient);
		
		// to switch and test screens
		//gui.showLoginScreen();
		//gui.showPlayerLobbyScreen();
		//gui.showPlayerTableScreen();
		//gui.showDealerLobbyScreen();
		gui.showDealerTableScreen();
		

	}
}
