package org.queenns.tool.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

/**
 * Created by lxj on 18-1-31
 */
public class InfoWrapperHandler {

    private InfoWrapperParserDelegate infoWrapperParserDelegate = new InfoWrapperParserDelegate();

    private ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    public InfoWrapperHandler() {
        super();
    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        InputStream inputStream = classLoader.getResourceAsStream("wechat-rule-define.xml");

        InputSource inputSource = new InputSource(inputStream);

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.parse(inputSource);

        Element root = document.getDocumentElement();

        parser(root);

        inputStream.close();

        /*XMLReader xmlReader = XMLReaderFactory.createXMLReader();

        InfoWrapperHandler infoWrapperHandler = new InfoWrapperHandler();

        xmlReader.setContentHandler(infoWrapperHandler);
        xmlReader.setErrorHandler(infoWrapperHandler);

        xmlReader.parse(inputSource);

        inputStream.close();*/

    }

    public static void parser(Element root) {

        NodeList nodes = root.getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++) {

            Node node = nodes.item(i);

            if (node instanceof Element) {

                System.out.println(((Element) node).getTagName());

                parser((Element) node);

            }

        }

    }

}