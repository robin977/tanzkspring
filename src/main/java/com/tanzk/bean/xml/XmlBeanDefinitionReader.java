package com.tanzk.bean.xml;

import com.tanzk.bean.BeanDefinition;
import com.tanzk.bean.BeanReference;
import com.tanzk.bean.PropertyValue;
import com.tanzk.bean.io.ResourceLoader;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;

/**
 * <p>功能描述：</p>
 *
 * @author robin
 * @date 2017/10/27
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        InputStream inputStream=getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinitions(inputStream);
    }


    protected void doLoadBeanDefinitions(InputStream inputStream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //解析成文档
        Document document=factory.newDocumentBuilder().parse(inputStream);
        parseBeanDefinitions(document);
        inputStream.close();
    }

    public void parseBeanDefinitions(Document document){
        Element root=document.getDocumentElement();
        NodeList nodeList=root.getChildNodes();
        for(int i=0;i<nodeList.getLength();i++){
           Node node= nodeList.item(i);
           if(node instanceof  Element){
               Element element=(Element) node;
               processBeanDefinition(element);
           }
        }
    }

    protected void processBeanDefinition(Element ele) {
        String name=ele.getAttribute("id");
        String className=ele.getAttribute("class");
        BeanDefinition beanDefinition=new BeanDefinition();
        beanDefinition.setBeanClassName(className);
        processProperty(ele,beanDefinition);
        getRegistry().put(name,beanDefinition);
    }

    //分析每个bean的属性
    private void processProperty(Element ele, BeanDefinition beanDefinition) {
        NodeList propertyNode= ele.getElementsByTagName("property");
        for(int i=0;i<propertyNode.getLength();i++){
            Node node=propertyNode.item(i);
            if(node instanceof  Element){
                 Element propertyEle=(Element) node;
                String name = propertyEle.getAttribute("name");
                String value = propertyEle.getAttribute("value");
                if(StringUtils.isNotEmpty(name)&&StringUtils.isNotEmpty(value)){
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name,value));
                }else{
                    String ref = propertyEle.getAttribute("ref");
                    BeanReference beanReference=new BeanReference(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name,beanReference));
                }
            }

        }
    }


    }
