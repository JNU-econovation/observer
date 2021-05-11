package com.capstone.apm.util;

public class UrlParser {
    
    private static String prefixHost = "//";
    private static String suffixHost = "/";
    
    public static String parseToHost(String url) {
        int beginIndex = url.indexOf(prefixHost) + prefixHost.length();
        int endIndex = url.indexOf(suffixHost, beginIndex);
        String host = url.substring(beginIndex, endIndex);
        return host;
    }
}
