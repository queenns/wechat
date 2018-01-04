package org.queenns.tool;

import org.apache.commons.httpclient.HttpMethod;
import org.queenns.tool.access.Access;
import org.queenns.tool.access.DisposeTransform;
import org.queenns.tool.access.GetResourceAccess;
import org.queenns.tool.util.Jackson;

import java.io.IOException;
import java.util.Map;

/**
 * Created by lxj on 17-12-19
 */
public class Test {

    public static String url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=218.4.255.255";

    public static void main(String[] args) throws IOException {

        Map map = new GetResourceAccess<Map>(url, Map.class).access();

        System.out.println(map);

    }

}
