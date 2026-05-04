package com.blackjack.BlackJackClientServer.src.network;

import java.io.*;
import java.net.*;
import java.util.*;

class Client {
    public static void main(String[] args){
        try (Socket socket = new Socket("localhost", 1999)) {
          
            // writing to server
            PrintWriter out = new PrintWriter(
                socket.getOutputStream(), true);

            // reading from server
            BufferedReader in
                = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            // object of scanner class
            Scanner sc = new Scanner(System.in);
            String line = null;

            while (!"exit".equalsIgnoreCase(line)) {
              
                // reading from user
                line = sc.nextLine();
                // End Connection 
                if(line.equals("End") || line.equals("end")) {
                	socket.close();
                	sc.close();
                	System.out.println("Connection Terminated!");
                	break;
                }else {
                // sending the user input to server
                out.println(line);
                out.flush();
                System.out.println("Server recieved message: " + line);
                }
            }
            sc.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
