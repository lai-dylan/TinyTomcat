package com.dylan.utils.xml;

import com.dylan.tomcat.servlet.HttpServlet;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author: DYLAN
 * Date: 9/11/2023
 * Description: 解析XML，反射创建对象
 */
public class XMLUtils {

    public static String xmlPath =  Objects.requireNonNull(XMLUtils.class.getResource("/")).getPath() + "web.xml";

    public static Element loadXMLRootElement(){
        SAXReader saxReader = new SAXReader();
        Document document;
        try {
            document = saxReader.read(xmlPath);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        return document.getRootElement();
    }

    public static void initServletInstanceMap(ConcurrentHashMap<String, HttpServlet> map){
        Element rootElement = loadXMLRootElement();
        List<Element> servletList = rootElement.elements("servlet");

        for (Element e : servletList) {
            String servletName = e.element("servlet-name").getText();
            String servletClassName = e.element("servlet-class").getText();
            try {
                Class<?> clazz = Class.forName(servletClassName);
                HttpServlet httpServlet = (HttpServlet)clazz.newInstance();
                map.put(servletName, httpServlet);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void initServletUrlMap(ConcurrentHashMap<String, String> map){
        Element rootElement = loadXMLRootElement();
        List<Element> servletMappingList =  rootElement.elements("servlet-mapping");

        for (Element e : servletMappingList) {
            String servletName = e.element("servlet-name").getText();
            String servletUrl = e.element("servlet-mapping").getText();
            map.put(servletUrl, servletName);
        }
    }

}
