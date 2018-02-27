package org.queenns.tool.xml;

import org.w3c.dom.Document;

/**
 * Created by lxj on 18-2-22
 */
public class XmlReaderContext {

    private AbstractParserDelegate abstractParserDelegate;

    private DocumentLoader documentLoader = new DefaultDocumentLoader();

    public XmlReaderContext(AbstractParserDelegate abstractParserDelegate) {
        this.abstractParserDelegate = abstractParserDelegate;
    }

    public AbstractParserDelegate getAbstractParserDelegate() {
        return abstractParserDelegate;
    }

    public void setAbstractParserDelegate(AbstractParserDelegate abstractParserDelegate) {
        this.abstractParserDelegate = abstractParserDelegate;
    }

    public void xxx() throws Exception {

        Document document = documentLoader.loadDocument(abstractParserDelegate.getInputSource(), abstractParserDelegate, abstractParserDelegate, 3, false);

    }

}
