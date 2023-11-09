package com.dylan.tomcat.handler;

import com.dylan.tomcat.Main;
import com.dylan.tomcat.http.Request;
import com.dylan.tomcat.http.Response;
import com.dylan.tomcat.servlet.HttpServlet;

import java.io.*;
import java.net.Socket;
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
            Request request = new Request(inputStream);

            OutputStream outputStream = socket.getOutputStream();
            Response response = new Response(outputStream);

            String servletUrl = Main.servletUrlMap.get(request.getUri());
            HttpServlet httpServlet = Main.servletInstanceMap.get(servletUrl);

            try {
                httpServlet.service(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            //释放资源
            inputStream.close();
            outputStream.flush();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
