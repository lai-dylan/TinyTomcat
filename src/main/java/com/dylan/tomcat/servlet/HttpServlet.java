package com.dylan.tomcat.servlet;

import com.dylan.tomcat.http.Request;
import com.dylan.tomcat.http.Response;

/**
 * Author: DYLAN
 * Date: 8/11/2023
 * Description: 抽象类，模板设计模式
 */
public abstract class HttpServlet implements Servlet{
    @Override
    public void service(Request request, Response response) throws Exception {
        if("GET".equalsIgnoreCase(request.getMethod())){
            this.doGet(request, response);
        }else if("POST".equalsIgnoreCase(request.getMethod())){
            this.doPost(request, response);
        }
    }

    public abstract void doGet(Request request, Response response);
    public abstract void doPost(Request request, Response response);
}
