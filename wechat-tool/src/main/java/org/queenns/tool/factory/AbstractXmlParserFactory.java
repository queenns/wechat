package org.queenns.tool.factory;

import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by lxj on 18-2-22
 */
public class AbstractXmlParserFactory implements XmlParserFactory {

    private String fileName;

    private ClassLoader classLoader;

    private DefaultHandler defaultHandler;

    public AbstractXmlParserFactory(String fileName, ClassLoader classLoader, DefaultHandler defaultHandler) {

        this.fileName = fileName;

        this.classLoader = classLoader != null ? classLoader : this.getClass().getClassLoader();

        this.defaultHandler = defaultHandler;

    }

    @Override
    public void parser() {

    }

}
