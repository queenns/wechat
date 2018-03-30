package org.queenns.tool.xml.extract;

import org.queenns.tool.exception.ExtractXmlException;
import org.w3c.dom.Element;

/**
 * Created by lxj on 18-3-30
 */
public interface Extract<T> {

    T extract(Element element) throws ExtractXmlException;

}
