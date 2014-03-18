package org.pengyuan.event;

import java.lang.reflect.Method;

import org.pengyuan.listener.SingletonActionListener;
import org.w3c.dom.Element;

public class EventHandler{
    private static SingletonActionListener listener = null;

    public static void handle(Object bean,Object component,Element eventNode){
        String listenerType = eventNode.getAttribute("type");
        if(listenerType.equals("ActionListener")){
            listener = SingletonActionListener.getInstance(bean,component,eventNode);
        }
        Class<?> componentClass = component.getClass();
        String addListener = "add" + listenerType;   //得到添加事件监听器的方法名
        try {
            Class<?> name = Class.forName("java.awt.event."+listenerType);
            Method method = componentClass.getMethod(addListener,name);
            method.invoke(component,listener);  //反射机制执行方法，可用Statement类替代
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}