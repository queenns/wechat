package org.queenns.tool.factory;

import org.queenns.tool.exception.ParserXmlException;
import org.queenns.tool.resource.Resource;
import org.queenns.tool.xml.AbstractParserDelegate;
import org.queenns.tool.xml.DefaultDocumentLoader;
import org.queenns.tool.xml.DocumentLoader;
import org.queenns.tool.xml.extract.UrlExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.*;

/**
 * Created by lxj on 18-2-22
 */
public abstract class AbstractXmlParserFactory implements XmlParserFactory {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private Resource resource;

    private AbstractParserDelegate abstractParserDelegate;

    private DocumentLoader documentLoader = new DefaultDocumentLoader();

    protected AbstractXmlParserFactory() {

        install();

    }

    /**
     * 初始化参数
     */
    protected abstract void install();

    void parser(Element element) {

        if (element.getTagName().equals("url")){
            //new UrlExtractor().extract(element);
        }

        NodeList nodes = element.getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++) {

            Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                logger.debug("------------------------------------------");

                logger.debug("text[{}]URI[{}]tag[{}]", node.getTextContent(),node.getNamespaceURI(), ((Element) node).getTagName());

                parser((Element) node);

                logger.debug("------------------------------------------");

            }

        }

    }

    @Override
    public void parser() throws ParserXmlException {

        try {

            Document document = documentLoader.loadDocument(resource, abstractParserDelegate);

            parser(document.getDocumentElement());

            logger.info(document.toString());

        } catch (Exception exception) {

            throw new ParserXmlException(resource, exception);

        }

    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public AbstractParserDelegate getAbstractParserDelegate() {
        return abstractParserDelegate;
    }

    public void setAbstractParserDelegate(AbstractParserDelegate abstractParserDelegate) {
        this.abstractParserDelegate = abstractParserDelegate;
    }

}
