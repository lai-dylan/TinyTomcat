package com.dylan.tomcat.http;

import lombok.Getter;

import java.io.OutputStream;

/**
 * Author: DYLAN
 * Date: 8/11/2023
 * Description: 服务器响应对象
 */
@Getter
public class Response {
    private OutputStream outputStream;

    public static final String respHeader = "HTTP/1.1 200 OK\n" +
            "Content-Type: text/html;charset=utf-8\n";

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }


}
