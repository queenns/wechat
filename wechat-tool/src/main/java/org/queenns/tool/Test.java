package org.queenns.tool;

import org.queenns.tool.access.AccessTokenAccess;
import org.queenns.tool.exception.ParserXmlException;
import org.queenns.tool.factory.InfoWrapperFactory;
import org.queenns.tool.main.WechatTool;



import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ClassUtils;


import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Map;

/**
 * Created by lxj on 17-12-19
 */
public class Test {

    public static String url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=218.4.255.255";

    public static void main(String[] args) throws ParserXmlException {

        InfoWrapperFactory.getInstance().parser();

        /*String xxx = WechatTool.call("accessToken");

        Map map = new AccessTokenAccess(url).access();

        Map map = new GetResourceAccess<Map>(url).access();

        System.out.println(map);*/

    }

}
