package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread{
    private Socket socket;
    private ArrayList<ServerThread> threadList;
    private PrintWriter output;

    public ServerThread(Socket socket, ArrayList<ServerThread> threadList) {
        this.socket = socket;
        this.threadList = threadList;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            while (true){
                String outputString = input.readLine();
                if (outputString.equals("QUIT")){
                    break;
                }
                printToAll(outputString);
                System.out.println("Server: Ten:" + outputString);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printToAll(String outputString){
        for (ServerThread thread : threadList){
            thread.output.println(outputString);
        }
    }

}