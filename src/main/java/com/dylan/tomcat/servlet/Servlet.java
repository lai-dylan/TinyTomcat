package com.dylan.tomcat.servlet;

import com.dylan.tomcat.http.Request;
import com.dylan.tomcat.http.Response;

/**
 * Author: DYLAN
 * Date: 8/11/2023
 * Description: Servlet接口
 */
public interface Servlet {
    void init() throws Exception;

    void service(Request request, Response response) throws Exception;

    void destroy();
}
