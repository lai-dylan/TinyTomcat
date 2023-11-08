package com.dylan.utils.request;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: DYLAN
 * Date: 8/11/2023
 * Description: RequestUtils
 */
public abstract class RequestUtils {

    public static String getUri(@NotNull String uriStr){
        int index = uriStr.indexOf('?');
        return index == -1 ? uriStr : uriStr.substring(0, index);
    }

    public static Map<String, String> getArgsMap(@NotNull String argsStr){
        Map<String, String> map = new HashMap<>();
        int index = argsStr.indexOf('?');
        if(index == -1 || index == argsStr.length() - 1){
            return null;
        }

        // /index?a=1
        String str = argsStr.substring(index + 1);
        if(!str.contains("&")) {
            String[] split = str.split("=");
            map.put(split[0], split[1]);
        }

        // /index?a=1&b=1
        for (String s : str.split("&")) {
            String[] split = s.split("=");
            map.put(split[0], split[1]);
        }

        return map;
    }
}
