package org.queenns.tool;

import org.queenns.tool.access.AccessTokenAccess;
import org.queenns.tool.main.WechatTool;

import java.io.IOException;
import java.util.Map;

/**
 * Created by lxj on 17-12-19
 */
public class Test {

    public static String url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=218.4.255.255";

    public static void main(String[] args) throws IOException {

        String xxx = WechatTool.call("accessToken");

        Map map = new AccessTokenAccess(url).access();

        /*Map map = new GetResourceAccess<Map>(url).access();*/

        System.out.println(map);

    }

}
