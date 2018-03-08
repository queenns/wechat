package org.queenns.tool.xml;

import org.queenns.tool.resource.Resource;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;

/**
 * Created by lxj on 18-3-6
 */
public interface ParserDelegate {

    int getValidationMode(Resource resource);

    boolean getNamespaceAware();

    ErrorHandler getErrorHandler();

    EntityResolver getEntityResolver();

}
