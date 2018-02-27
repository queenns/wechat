package org.queenns.tool.factory;

import org.queenns.tool.model.Information;
import org.queenns.tool.xml.InfoWrapperParserDelegate;

import java.util.LinkedHashMap;

/**
 * Created by lxj on 18-2-22
 */
public class InfoWrapperFactory extends AbstractXmlParserFactory {

    private static final String fileName = "wechat-rule-define.xml";

    private LinkedHashMap<String, Information> informations = new LinkedHashMap<>();

    public InfoWrapperFactory() {

        super(fileName, null, new InfoWrapperParserDelegate());

    }

}
