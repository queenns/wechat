package org.queenns.tool.xml;

import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by lxj on 18-2-22
 * <p>
 * 装载XML实现,默认DTD效验，所以开启XSD效验
 * <p>
 * <reference source Spring>
 */
public class DefaultDocumentLoader implements DocumentLoader {

    // JAXP解析,Schema的定义
    private static final String SCHEMA_LANGUAGE_ATTRIBUTE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

    // xsd验证定义
    private static final String XSD_SCHEMA_LANGUAGE = "http://www.w3.org/2001/XMLSchema";

    @Override
    public Document loadDocument(InputSource inputSource, EntityResolver entityResolver, ErrorHandler errorHandler, int validationMode, boolean namespaceAware) throws Exception {

        DocumentBuilderFactory documentBuilderFactory = createDocumentBuilderFactory(validationMode, namespaceAware);

        DocumentBuilder documentBuilder = createDocumentBuilder(documentBuilderFactory, entityResolver, errorHandler);

        return documentBuilder.parse(inputSource);

    }

    protected DocumentBuilderFactory createDocumentBuilderFactory(int validationMode, boolean namespaceAware) throws ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // 设置是否提供对XML名称空间的支持
        factory.setNamespaceAware(namespaceAware);

        // 是否验证(0:禁用验证)
        factory.setValidating(validationMode != 0);

        // 启用命名空间的支持(3:xsd验证模式)
        if (validationMode == 3) factory.setNamespaceAware(true);

        try {

            factory.setAttribute(SCHEMA_LANGUAGE_ATTRIBUTE, XSD_SCHEMA_LANGUAGE);

        } catch (IllegalArgumentException e) {

            ParserConfigurationException parserConfigurationException = new ParserConfigurationException("Unable to validate using XSD: Your JAXP provider [" + factory + "] does not support XML Schema. Are you running on Java 1.4 with Apache Crimson? " + "Upgrade to Apache Xerces (or Java 1.5) for full XSD support.");

            parserConfigurationException.initCause(e);

            throw parserConfigurationException;

        }

        return factory;

    }

    protected DocumentBuilder createDocumentBuilder(DocumentBuilderFactory documentBuilderFactory, EntityResolver entityResolver, ErrorHandler errorHandler) throws ParserConfigurationException {

        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        if (entityResolver != null) documentBuilder.setEntityResolver(entityResolver);

        if (errorHandler != null) documentBuilder.setErrorHandler(errorHandler);

        return documentBuilder;

    }

}
