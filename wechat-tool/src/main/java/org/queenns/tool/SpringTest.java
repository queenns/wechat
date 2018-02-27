package org.queenns.tool;

import org.queenns.tool.model.Information;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * Created by lxj on 18-2-22
 */
public class SpringTest {

    public static void main(String[] args) throws IOException {

        Resource resource = new ClassPathResource("applicationContext.xml");

        resource.getURL();

        BeanFactory beanFactory = new XmlBeanFactory(resource);

        Information information = beanFactory.getBean("information", Information.class);

        System.out.println(information);

    }

}
