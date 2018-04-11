package org.queenns.tool.xml.extract;

import org.queenns.tool.access.Method;
import org.queenns.tool.exception.ExtractXmlException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Created by lxj on 18-4-11
 */
@BaseExtractor(element = "method")
public class MethodExtractor extends Extractor<Method> {

    private static final String ELEMENT_NAME = "method";

    @Override
    public Method extract(Element element) throws ExtractXmlException {

        Node node = element.getFirstChild();

        validateNodeType(ELEMENT_NAME, node.getNodeType(), Node.TEXT_NODE);

        String value = node.getNodeValue();

        validateValueNull(ELEMENT_NAME, value);

        return Method.valueOf(value);

    }

}
