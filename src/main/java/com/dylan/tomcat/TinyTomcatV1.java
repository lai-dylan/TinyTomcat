package com.dylan.tomcat;

import com.dylan.tomcat.handler.RequestsHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * Author: DYLAN
 * Date: 8/11/2023
 * Description: 用户请求接收中心，只分发，不处理
 */
public class TinyTomcatV1 {
    public static final Logger LOGGER = Logger.getLogger(TinyTomcatV1.class.getName());

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        LOGGER.info("Tomcat在端口8080处监听中...\n");

        while (!serverSocket.isClosed()) {
            Socket socket = serverSocket.accept();
            new Thread(new RequestsHandler(socket)).start();
        }
    }
}
