package org.pengyuan.util;

import org.pengyuan.event.BeanFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class XMLReader {
    private Element rootNode;
    public XMLReader(String fileName){
        DOMParser parse = new DOMParser();
        try {
            parse.parse(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Document doc = parse.getDocument();   //预处理xml文件，转换为doc文档类
        rootNode = doc.getDocumentElement();
    }
    public Object getBean(String id){   //得到指定容器的实例
        Object obj = null;
        NodeList beanNodeList = rootNode.getElementsByTagName("bean");
        for(int i=0;i<beanNodeList.getLength();i++) {
            Element beanNode = (Element) beanNodeList.item(i);
            if(beanNode.getAttribute("id").equals(id)){
                obj = BeanFactory.create(beanNode);
            }
        }
        return obj;
    }
}