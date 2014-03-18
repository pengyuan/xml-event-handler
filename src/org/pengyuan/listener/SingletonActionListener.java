package org.pengyuan.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.w3c.dom.Element;
 

public class SingletonActionListener implements ActionListener{
    private static HashMap<Object,Element> eventMap = new HashMap<Object,Element>();;
    private static HashMap<String,Object> classMap = new HashMap<String,Object>();;
    private static SingletonActionListener singletonActionListener = new SingletonActionListener();    //单态模式的ActionListener，只有一个实例在内存

    public static SingletonActionListener getInstance(Object bean,Object component,Element eventNode){
        String classPath = eventNode.getAttribute("class");
        if(classPath==""){  //若event节点中class属性缺省，则默认事件执行方法在原容器中
            String className = bean.getClass().getName();
            eventNode.setAttribute("class", className);
            classMap.put(className, bean);  //classMap用于保证执行器的单态，节省内存
        }
        eventMap.put(component, eventNode);
        return singletonActionListener;
    }

    public void actionPerformed(ActionEvent event) {
        Object key = event.getSource();
        Object execute = null;
        if(eventMap.containsKey(key)){
            Element eventNode = eventMap.get(key);
            String classPath = eventNode.getAttribute("class");
            try {
                if(classMap.containsKey(classPath)){
                    execute = classMap.get(classPath);  //返回事件执行器
                }
                else{
                    execute = Class.forName(classPath).newInstance(); //实例化事件执行器
                    classMap.put(classPath, execute);    //置入classMap，保证单态
                }
                String targetMethod = eventNode.getAttribute("method"); //反射
                Class<?> cla = execute.getClass();
                Method m = cla.getMethod(targetMethod,new Class[]{});
                m.invoke(execute, new Object[]{});  } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}