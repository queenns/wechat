package org.queenns.tool.xml.extract;

import org.queenns.tool.exception.ExtractXmlException;
import org.queenns.tool.util.StringUtil;
import org.w3c.dom.Node;

/**
 * Created by lxj on 18-3-30
 */
public abstract class Extractor<T> implements Extract<T> {

    /**
     * 检查节点类型
     *
     * @param elementName 节点名称
     * @param sourceType  　节点原始类型
     * @param confirmType 　节点确认类型
     * @throws ExtractXmlException 确认节点类型不匹配时抛出
     */
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

    /**
     * 检查节点是否为空
     *
     * @param elementName 节点名称
     * @param value       节点值
     * @throws ExtractXmlException 当值为空时抛出
     */
    protected void validateValueNull(String elementName, String value) throws ExtractXmlException {

        if (StringUtil.isEmpty(value))

            throw new ExtractXmlException("Element " + elementName + " value must not be null");

    }

}
