package org.queenns.tool.xml.extract;

import org.queenns.tool.exception.ExtractXmlException;
import org.queenns.tool.util.StringUtil;
import org.w3c.dom.Node;

/**
 * Created by lxj on 18-3-30
 */
public abstract class Extractor<T> implements Extract<T> {

    protected void validateNodeType(String elementName, int sourceType, int confirmType) throws ExtractXmlException {

        if (sourceType == confirmType) return;

        if (confirmType == Node.ELEMENT_NODE)

            throw new ExtractXmlException("Element " + elementName + " value type must be org.w3c.dom.Node.ELEMENT_NODE");

        else if (confirmType == Node.ATTRIBUTE_NODE)

            throw new ExtractXmlException("Element " + elementName + " value type must be org.w3c.dom.Node.ATTRIBUTE_NODE");

        else if (confirmType == Node.TEXT_NODE)

            throw new ExtractXmlException("Element " + elementName + " value type must be org.w3c.dom.Node.TEXT_NODE");

        else if (confirmType == Node.CDATA_SECTION_NODE)

            throw new ExtractXmlException("Element " + elementName + " value type must be org.w3c.dom.Node.CDATA_SECTION_NODE");

        else

            throw new ExtractXmlException("Element " + elementName + " value type sourceType[" + sourceType + "] != confirmType[" + confirmType + "]");

    }

    protected void validateValueNull(String elementName, String value) throws ExtractXmlException {

        if (StringUtil.isEmpty(value))

            throw new ExtractXmlException("Element " + elementName + " value must not be null");

    }

}
