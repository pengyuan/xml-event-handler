package org.pengyuan.event;

import java.beans.Expression;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class BeanFactory {
    public static Object create(Element beanNode){ //工厂静态方法
        Object bean = null;
        try {
            bean = Class.forName(beanNode.getAttribute("class")).newInstance();
            NodeList componentNodeList = beanNode.getElementsByTagName("component");
            for(int i=0;i<componentNodeList.getLength();i++){
                Element componentNode = (Element)componentNodeList.item(i);
                String componentName = componentNode.getAttribute("name");

                String firstChar = componentName.charAt(0) + "";
                String newFirstChar = firstChar.toUpperCase();
                String methodName = "get" + componentName.replaceFirst(firstChar, newFirstChar);               //得到get函数，进而得到容器内的组件，如Button

                Expression e=new Expression(bean,methodName,null);
                e.execute();
                Object component = e.getValue(); //Expression用于执行给定类中的指定方法，并得到返回值。这是Java反射机制的一种替代方案，执行效率更高。

                NodeList eventNodeList = componentNode.getElementsByTagName("event");
                for(int j=0;j<eventNodeList.getLength();j++){
                    Element eventNode = (Element)eventNodeList.item(j);
                    EventHandler.handle(bean,component,eventNode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}