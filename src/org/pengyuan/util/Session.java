package org.pengyuan.util;

import java.util.HashMap;

public class Session {
    private static HashMap<String,Object> hm = new HashMap<String,Object>();
    public static void setAttribute(String name,Object obj){ //静态方法，添加属性
        hm.put(name, obj);
    }
    public static Object getAttribute(String name){ //静态方法，得到属性
        Object obj = hm.get(name);
        return obj;
    }
}