package com.dylan.tomcat.servlet;

import com.dylan.tomcat.http.Request;
import com.dylan.tomcat.http.Response;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Author: DYLAN
 * Date: 8/11/2023
 * Description: TODO
 */
public class CalculateServlet extends HttpServlet{
    @Override
    public void doGet(Request request, Response response) {
        String num1 = request.getParameter("num1");
        String num2 = request.getParameter("num2");
       int total = Integer.parseInt(num1) + Integer.parseInt(num2);

        OutputStream outputStream = response.getOutputStream();
        try {
            outputStream.write((Response.respHeader + "\n" + "<h1>num1+num2=</h1>" + total).getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doPost(Request request, Response response) {
        this.doGet(request, response);
    }

    @Override
    public void init() throws Exception {
        System.out.println("init...");
    }

    @Override
    public void destroy() {
        System.out.println("destroy...");
    }
}
