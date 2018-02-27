package org.queenns.tool.xml;

import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;

/**
 * Created by lxj on 18-2-22
 * <p>
 * 用于装载XML的策略接口
 * <p>
 * <reference source Spring>
 */
public interface DocumentLoader {

    /**
     * @param inputSource    加载的文档源
     * @param entityResolver 解析任何实体的解析器
     * @param errorHandler   报告加载期间的任何错误
     * @param validationMode xml验证模式
     * @param namespaceAware 是否提供对XML名称空间的支持
     * @return {@link Document document}
     * @throws Exception throws Exception
     */
    Document loadDocument(InputSource inputSource, EntityResolver entityResolver, ErrorHandler errorHandler, int validationMode, boolean namespaceAware) throws Exception;

}
