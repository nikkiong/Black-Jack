package com.blackjack.testing;

// Temporary GUI test class to run and debug screens without backend/client
import com.blackjack.client.gui.GUIManager;

public class GUITest {
	
	public static void main(String[] args)
	{

		// use dummy client for testing
	    FakeClientGUITester testClient = new FakeClientGUITester();
		
		GUIManager gui = new GUIManager(testClient);
		
		gui.showPlayerTableScreen();
		

	}
}
