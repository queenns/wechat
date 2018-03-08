package org.queenns.tool.factory;

import org.queenns.tool.resource.ClassPathResource;
import org.queenns.tool.xml.InfoWrapperParserDelegate;

/**
 * Created by lxj on 18-2-22
 */
public class InfoWrapperFactory extends AbstractXmlParserFactory {

    private InfoWrapperFactory() {

        super();

    }

    private static class Singleton{

        private static final InfoWrapperFactory infoWrapperFactory = new InfoWrapperFactory();

    }

    public static InfoWrapperFactory getInstance(){

        return Singleton.infoWrapperFactory;

    }

    @Override
    protected void install() {

        setResource(new ClassPathResource("wechat-rule-define.xml"));

        setAbstractParserDelegate(new InfoWrapperParserDelegate());

    }

}
