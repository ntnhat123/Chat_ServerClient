package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientThread extends Thread{
    private Socket socket;
    private BufferedReader reader;
    private ArrayList<ClientThread> threadList;
    private PrintWriter output;

    public ClientThread(Socket s) throws IOException {
        this.socket = s;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            while (true){
                String message = reader.readLine();
                if (message == null){
                    break;
                }
                System.out.println(message +"");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void close() throws IOException {
        socket.close();
    }

}