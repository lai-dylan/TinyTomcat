package com.dylan.tomcat.handler;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

/**
 * Author: DYLAN
 * Date: 8/11/2023
 * Description: 对每个请求分配个线程进行处理
 */
public class RequestsHandler implements Runnable{
    public static final Logger LOGGER = Logger.getLogger(RequestsHandler.class.getName());
    private Socket socket;

    public RequestsHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            LOGGER.info("线程{" +Thread.currentThread().getName() +"}接收到来自浏览器的数据为:");
            String msg = null;
            while ((msg = bufferedReader.readLine()) != null) {
                if (msg.isEmpty()) break;
                System.out.println(msg);
            }
            //回送数据
            OutputStream outputStream = socket.getOutputStream();
            String respHeader = "HTTP/1.1 200 OK\n" +
                    "Content-Type: text/html;charset=utf-8\n";
            String respBody ="<h1>Hi TinyTomcat</h1>";
            String resp = respHeader + "\n" + respBody;
            LOGGER.info("线程{" +Thread.currentThread().getName() +"}回送给浏览器的数据为:\n" + resp);
            outputStream.write(resp.getBytes());

            //释放资源
            bufferedReader.close();
            outputStream.flush();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
