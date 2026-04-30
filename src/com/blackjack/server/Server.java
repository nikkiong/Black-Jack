package com.blackjack.server;
import java.io.IOException;
import java.net.ServerSocket;

public class Server {
	ServerSocket ss;
	
	Server() throws IOException {
	}
	
	public static void main(String args[]) throws IOException, ClassNotFoundException {
		System.out.println("Test");
		
		
		
	}
	
	public void startServer() throws IOException, ClassNotFoundException {
		//Server socket
		this.ss = new ServerSocket(7777);
		System.out.println("Server socket connected");
		
	}
}
