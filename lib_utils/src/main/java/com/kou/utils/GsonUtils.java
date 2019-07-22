package com.kou.utils;


import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class GsonUtils {
    public static <T> T getBean(String json, Type t) {
        Gson gson = new Gson();
        T t1 = gson.fromJson(json, t);
        return t1;
    }

    public static <T> T getBean(String json, Class<T> clazz) {
        Gson gson = new Gson();
        T t1 = gson.fromJson(json, clazz);
        return t1;
    }

    public static String toJson(Object o) {
        Gson gson = new Gson();
        return gson.toJson(o);
    }

    public static <T> List<T> getList(String json, Type t) throws JSONException {
        List<T> list = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(json);
        Gson gson = new Gson();
        for (int i = 0; i < jsonArray.length(); i++) {
            T t1 = gson.fromJson(jsonArray.getString(i), t);
            list.add(t1);
        }
        return list;
    }
}
