package com.daiwei.socket.app;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Daiwei on 2021/9/9
 */
public class SocketAppClient {

    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            socketSendMsg("hello server of " + i);
        }
    }

    /**
     * 通过socket 发送消息到server端
     * @param msg
     */
    private static void socketSendMsg(String msg) {
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.write(msg);
            printWriter.flush();

            printWriter.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
