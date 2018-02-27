package org.queenns.tool.xml;

import org.queenns.tool.resource.Resource;
import org.w3c.dom.Document;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by lxj on 18-2-22
 */
public class XmlReader {

    private Resource resource;

    private DefaultHandler defaultHandler;

    private DocumentLoader documentLoader = new DefaultDocumentLoader();



    public void loadXml() throws Exception {

        //Document document = documentLoader.loadDocument(, abstractParserDelegate, abstractParserDelegate, 3, false);

    }



}
