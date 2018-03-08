package org.queenns.tool.xml;

import org.queenns.tool.resource.Resource;
import org.queenns.tool.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lxj on 18-2-5
 */
public abstract class AbstractParserDelegate extends DefaultHandler implements ParserDelegate {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * xml属性key[id]
     */
    public static final String ATTRIBUTION_ID = "id";

    /**
     * xml验证模式
     */
    private boolean namespaceAware = false;

    /**
     * xml是否提供名称空间的支持
     */
    private int validationMode = XmlValidationModeDetector.VALIDATION_AUTO;

    /**
     * 加载期间的任何错误处理器
     */
    private ErrorHandler errorHandler = new DefaultErrorHandler(logger);

    /**
     * 解析任何实体的解析器
     */
    private EntityResolver entityResolver = new DefaultEntityResolver();

    /**
     * XML验证模式检测器
     */
    private XmlValidationModeDetector xmlValidationModeDetector = new XmlValidationModeDetector();

    public AbstractParserDelegate() {
        super();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        StringBuilder value = new StringBuilder();

        for (int i = start; i < start + length; i++) {

            if (ch[i] == ' ') continue;

            if (ch[i] == '\n') continue;

            value.append(ch[i]);

        }

        if (!StringUtil.isEmpty(value.toString())) System.out.print(value.toString());

    }

    @Override
    public int getValidationMode(Resource resource) {

        if (validationMode != XmlValidationModeDetector.VALIDATION_AUTO) return validationMode;

        try (InputStream inputStream = resource.getInputStream()) {

            int mode = this.xmlValidationModeDetector.detectValidationMode(inputStream);

            validationMode = (mode == XmlValidationModeDetector.VALIDATION_AUTO ? XmlValidationModeDetector.VALIDATION_XSD : mode);

            return this.validationMode;

        } catch (IOException e) {

            RuntimeException runtimeException = new RuntimeException("Unable to determine validation mode,an error occurred whilst reading from the InputStream.Root message " + e.getMessage(), e.getCause());

            runtimeException.setStackTrace(e.getStackTrace());

            throw runtimeException;

        }

    }

    @Override
    public boolean getNamespaceAware() {

        return this.namespaceAware;

    }

    @Override
    public ErrorHandler getErrorHandler() {

        return this.errorHandler;

    }

    @Override
    public EntityResolver getEntityResolver() {

        return this.entityResolver;

    }

}
