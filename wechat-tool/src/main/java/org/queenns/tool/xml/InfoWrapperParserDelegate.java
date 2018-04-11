package org.queenns.tool.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Created by lxj on 18-2-5
 */
public class InfoWrapperParserDelegate extends AbstractParserDelegate {

    public static final String WRAPPER_NAMESPACE_URI = "http://www.queenns.org/schema/wrapper";

    private static final String ELEMENT_GROUP = "group";

    private static final String ELEMENT_INFORMATION = "information";

    private static final String ELEMENT_INFORMATION_METHOD = "method";

    private static final String ELEMENT_INFORMATION_URL = "url";

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        switch (qName) {

            case ELEMENT_GROUP:
                break;

            case ELEMENT_INFORMATION:
                break;

            case ELEMENT_INFORMATION_METHOD:
                break;

            case ELEMENT_INFORMATION_URL:
                break;

        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {


    }

    protected void parseElementGroup() {



    }

}
