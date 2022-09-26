package com.company;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) {
        ArrayList<ServerThread> threadList = new ArrayList<>();
        try (ServerSocket serverSocket = new ServerSocket(4800)){
            while (true){
                Socket socket = serverSocket.accept();
                ServerThread thread = new ServerThread(socket, threadList);
                threadList.add(thread);
                thread.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}