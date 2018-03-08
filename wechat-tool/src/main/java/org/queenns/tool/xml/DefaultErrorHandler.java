package org.queenns.tool.xml;

import org.slf4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Created by lxj on 18-3-6
 */
public class DefaultErrorHandler implements ErrorHandler {

    private Logger logger;

    public DefaultErrorHandler(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        logger.warn("ErrorHandler warning {}", exception);
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        throw exception;
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        throw exception;
    }

}
