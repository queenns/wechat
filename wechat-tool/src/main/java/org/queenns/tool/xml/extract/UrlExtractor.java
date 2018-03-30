package org.queenns.tool.xml.extract;

import org.queenns.tool.exception.ExtractXmlException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Created by lxj on 18-3-30
 */
@BaseExtractor(element = "url")
public class UrlExtractor extends Extractor<String> {

    private static final String ELEMENT_NAME = "url";

    @Override
    public String extract(Element element) throws ExtractXmlException {

        Node node = element.getFirstChild();

        validateNodeType(ELEMENT_NAME, node.getNodeType(), Node.CDATA_SECTION_NODE);

        String value = node.getNodeValue();

        validateValueNull(ELEMENT_NAME, value);

        return value;

    }

}
