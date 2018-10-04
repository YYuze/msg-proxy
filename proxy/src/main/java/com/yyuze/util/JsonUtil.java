package com.yyuze.util;

import com.google.gson.Gson;

/**
 * Author: yyuze
 * Created: 2018/9/17
 */
public class JsonUtil {

    public static <T> T fromJson(String jsonStr, Class<T> clz) {
        System.out.println(jsonStr);
        return new Gson().fromJson(jsonStr,clz);
    }

    public static <T> String toJson(T object) {
        return new Gson().toJson(object);
    }
}
