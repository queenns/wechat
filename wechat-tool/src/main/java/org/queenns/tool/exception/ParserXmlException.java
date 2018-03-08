package org.queenns.tool.exception;

import org.queenns.tool.resource.Resource;

/**
 * Created by lxj on 18-3-6
 */
public class ParserXmlException extends Exception {

    public ParserXmlException(Resource resource, Exception exception) {

        super("file [" + resource.getFilename() + "]" + exception.getMessage());

        initCause(exception.getCause());

        setStackTrace(exception.getStackTrace());

    }

    public ParserXmlException(Exception exception) {

        super(exception.getMessage());

        initCause(exception.getCause());

        setStackTrace(exception.getStackTrace());

    }

}
