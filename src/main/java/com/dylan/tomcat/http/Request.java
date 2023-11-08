package com.dylan.tomcat.http;

import com.dylan.utils.request.RequestUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: DYLAN
 * Date: 8/11/2023
 * Description: 浏览器请求对象
 */
@Getter
@ToString
public class Request {
    private final String method;
    private final String uri;
    private Map<String, String> argsMap = new HashMap<>();

    public Request(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String[] requestLine1Arr = bufferedReader.readLine().split(" ");
        method = requestLine1Arr[0];
        uri = RequestUtils.getUri(requestLine1Arr[1]);
        argsMap = RequestUtils.getArgsMap(requestLine1Arr[1]);
    }

    public String getParameter(String key){
        return argsMap.get(key);
    }
}
