package com.daiwei.socket.app;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Daiwei on 2021/9/9
 */
public class SocketAppServer {

    public static void main(String[] args) {
        int port = 8888;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("server is running and listening at " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                String res;
                while ((res= bufferedReader.readLine()) != null) {
                    System.out.println("message checked from [" + res + "]");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

