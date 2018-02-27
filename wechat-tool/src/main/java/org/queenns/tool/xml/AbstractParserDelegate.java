package org.queenns.tool.xml;

import org.queenns.tool.util.StringUtil;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by lxj on 18-2-5
 */
public abstract class AbstractParserDelegate extends DefaultHandler {

    public static final String ATTRIBUTION_ID = "id";

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

}
