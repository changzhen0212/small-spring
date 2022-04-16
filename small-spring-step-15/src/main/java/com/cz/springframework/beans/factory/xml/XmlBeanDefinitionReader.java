package com.cz.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import com.cz.springframework.beans.BeansException;
import com.cz.springframework.beans.PropertyValue;
import com.cz.springframework.beans.factory.config.BeanDefinition;
import com.cz.springframework.beans.factory.config.BeanReference;
import com.cz.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.cz.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.cz.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import com.cz.springframework.core.io.Resource;
import com.cz.springframework.core.io.ResourceLoader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/** @author ChangZhen */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException | DocumentException e) {
            throw new BeansException("IOException 解析XML文档异常 from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    /**
     * 解析xml文件
     *
     * <p>主要对xml的读取和元素Element解析。在解析的过程中通过循环操作，以此获取bean配置 以及配置中的 id、name、class、value、ref信息
     *
     * <p>最终把读取出来的配置信息 创建成BeanDefinition以及PropertyValue，最终把完整的BeanDefinition内容注册到Bean容器
     *
     * <p>step-13 处理新增的自定义配 置属性 component-scan，解析后调用 scanPackage 方法，其实也就是我们在
     * ClassPathBeanDefinitionScanner#doScan 功能
     *
     * <p>Element全部替换为 dom4j 的方式进行解析处理
     *
     * @param inputStream
     * @throws ClassNotFoundException
     */
    protected void doLoadBeanDefinitions(InputStream inputStream)
            throws ClassNotFoundException, DocumentException {
        // step-13
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();

        // step-13 解析context:component-scan标签，扫描包中的类并提取相关信息，用于组装BeanDefinition
        Element componentScan = root.element("component-scan");
        if (null != componentScan) {
            String scanPath = componentScan.attributeValue("base-package");
            if (StrUtil.isEmpty(scanPath)) {
                throw new BeansException("base-package 属性的值不能为empty或为null");
            }
            scanPackage(scanPath);
        }

        List<Element> beanList = root.elements("bean");

        // NodeList childNodes = root.getChildNodes();
        for (Element bean : beanList) {
            String id = bean.attributeValue("id");
            String name = bean.attributeValue("name");
            String className = bean.attributeValue("class");
            String initMethod = bean.attributeValue("init-method");
            String destroyMethod = bean.attributeValue("destroy-method");
            String beanScope = bean.attributeValue("scope");

            // 获取class,方便获取类中的名称
            Class<?> clazz = Class.forName(className);
            // 优先级 id > name
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            // 定义bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            beanDefinition.setInitMethodName(initMethod);
            beanDefinition.setDestroyMethodName(destroyMethod);
            if (StrUtil.isNotEmpty(beanScope)) {
                beanDefinition.setScope(beanScope);
            }

            List<Element> propertyList = bean.elements("property");
            // 读取属性并填充
            for (Element property : propertyList) {
                // 解析标签 property
                String attrName = property.attributeValue("name");
                String attrValue = property.attributeValue("value");
                String attrRef = property.attributeValue("ref");
                // 获取属性值： 引入对象、值对象
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                // 创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            // 注册beanDefinition
            getRegistry().registryBeanDefinition(beanName, beanDefinition);
        }
    }

    /**
     * 扫描包
     *
     * @param scanPath
     */
    private void scanPackage(String scanPath) {
        String[] basePackages = StrUtil.splitToArray(scanPath, ',');
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(getRegistry());
        scanner.doScan(basePackages);
    }
}
