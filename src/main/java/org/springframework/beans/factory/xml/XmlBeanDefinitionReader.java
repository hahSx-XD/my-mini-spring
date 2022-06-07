package org.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanReference;
import org.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * 读取配置在 xml 文件中的 bean 定义信息
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-07 19:21
 * @Version: 1.0
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    
    public static final String BEAN_ELEMENT = "bean";
    public static final String PROPERTY_ELEMENT = "property";
    public static final String ID_ATTRIBUTE = "id";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String VALUE_ATTRIBUTE = "value";
    public static final String REF_ATTRIBUTE = "ref";
    
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }
    
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }
    
    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }
    
    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            InputStream inputStream = resource.getInputStream();
            doLoadBeanDefinitions(inputStream);
        } catch (IOException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
        
    }
    
    protected void doLoadBeanDefinitions(InputStream inputStream) {
        //获得 Xml 文件内容流
        Document document = XmlUtil.readXML(inputStream);
        //获得文件根目录
        Element root = document.getDocumentElement();
        //获得根目录下的所有内容
        NodeList childNodes = root.getChildNodes();
        //循环查找、解析 bean 标签
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (childNodes.item(i) instanceof Element) {
                if (BEAN_ELEMENT.equals(childNodes.item(i).getNodeName())) {
                    Element bean = (Element) childNodes.item(i);
                    String id = bean.getAttribute(ID_ATTRIBUTE);
                    String nameBean = bean.getAttribute(NAME_ATTRIBUTE);
                    String className = bean.getAttribute(CLASS_ATTRIBUTE);
                    
                    Class<?> clazz = null;
                    try {
                        clazz = Class.forName(className);
                    } catch (ClassNotFoundException e) {
                        throw new BeansException("Cannot find class [" + className + "]");
                    }
                    //id 优先于 name
                    String beanName = StrUtil.isNotEmpty(id) ? id : nameBean;
                    if (StrUtil.isEmpty(beanName)) {
                        //如果 id 和 name 都为空，将类名第一个字母转为小写作为 bean 的名称
                        beanName = StrUtil.lowerFirst(clazz.getSimpleName());
                    }
                    
                    BeanDefinition beanDefinition = new BeanDefinition(clazz);
                    //循环加载当前 bean 的所有定义信息
                    for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                        if (bean.getChildNodes().item(j) instanceof Element) {
                            if (PROPERTY_ELEMENT.equals(bean.getChildNodes().item(j).getNodeName())) {
                                //解析 property 标签
                                Element property = (Element) bean.getChildNodes().item(j);
                                String nameProperty = property.getAttribute(NAME_ATTRIBUTE);
                                String valueProperty = property.getAttribute(VALUE_ATTRIBUTE);
                                String refProperty = property.getAttribute(REF_ATTRIBUTE);
                                
                                if (StrUtil.isEmpty(nameProperty)) {
                                    throw new BeansException("The property name cannot be null or empty");
                                }
                                
                                Object value = valueProperty;
                                if (StrUtil.isNotEmpty(refProperty)) {
                                    value = new BeanReference(refProperty);
                                }
                                PropertyValue propertyValue = new PropertyValue(nameProperty, value);
                                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                            }
                        }
                    }
                    
                    if (getRegistry().containsBeanDefinition(beanName)) {
                        //bean 名称不可以重复
                        throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
                    }
                    
                    //注册 BeanDefinition
                    getRegistry().registryBeanDefinition(beanName, beanDefinition);
                }
            }
        }
    }
}
