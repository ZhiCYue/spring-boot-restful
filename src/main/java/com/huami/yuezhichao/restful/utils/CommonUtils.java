package com.huami.yuezhichao.restful.utils;


import java.util.List;

public class CommonUtils {

    public static boolean has(List<String> arr, String str) {
        for (int i=0; i<arr.size(); i++) {
            String t = arr.get(i);
            if (str.equals(t)) return true;
        }
        return false;
    }

}
