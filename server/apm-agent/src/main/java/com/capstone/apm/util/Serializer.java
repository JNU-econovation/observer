package com.capstone.apm.util;

import com.google.gson.Gson;

public class Serializer {
    
    private static Gson gson;
    
    static {
        gson = new Gson();
    }
    
    public static String serialize(Object object) {
        return gson.toJson(object);
    }
}
