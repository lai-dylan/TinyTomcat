package com.dylan.tomcat;

import com.dylan.tomcat.handler.RequestsHandler;
import com.dylan.tomcat.servlet.HttpServlet;
import com.dylan.utils.xml.XMLUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * Author: DYLAN
 * Date: 8/11/2023
 * Description: 用户请求接收中心，只分发，不处理
 */
public class Main {
    public static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static final ConcurrentHashMap<String, HttpServlet> servletInstanceMap = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<String, String> servletUrlMap = new ConcurrentHashMap<>();

    public static void init(){
        XMLUtils.initServletInstanceMap(servletInstanceMap);
        XMLUtils.initServletUrlMap(servletUrlMap);
    }

    public static void run(){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("TinyTomcat在端口8080处监听中...\n");

        while (!serverSocket.isClosed()) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            new Thread(new RequestsHandler(socket)).start();
        }
    }

    public static void main(String[] args) {
        init();
        run();
    }
}
