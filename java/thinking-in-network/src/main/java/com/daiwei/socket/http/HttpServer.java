package com.daiwei.socket.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Daiwei on 2021/9/9
 */
public class HttpServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8081);
        while (true) {
            Socket socket = serverSocket.accept();
            service(socket);
        }
    }

    /**
     * socket 处理服务
     * @param socket
     */
    private static void service(Socket socket) {
        try {
            String body = "hello socket";
            StringBuilder response = new StringBuilder();
            response.append("HTTP/1.1 200 OK\r\n")
                    .append("Content-Length: ").append(body.getBytes().length).append("\r\n")
                    .append("Content-Type: text/plain; charset-utf-8\r\n")
                    .append("\r\n")
                    .append(body).append("\r\n");

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(response.toString().getBytes());
            outputStream.flush();

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
