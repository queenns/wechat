package org.queenns.tool.xml;

import org.queenns.tool.util.StringUtil;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by lxj on 18-2-5
 */
public abstract class AbstractParserDelegate extends DefaultHandler {

    public static final String ATTRIBUTION_ID = "id";

    private InputSource inputSource;

    public AbstractParserDelegate() {
        super();
    }

    public InputSource getInputSource() {
        return inputSource;
    }

    public void setInputSource(InputSource inputSource) {
        this.inputSource = inputSource;
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
