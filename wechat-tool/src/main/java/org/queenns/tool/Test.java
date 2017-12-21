package org.queenns.tool;

import org.queenns.tool.access.Access;
import org.queenns.tool.access.GetResourceAccess;

/**
 * Created by lxj on 17-12-19
 */
public class Test {

    public static void main(String[] args) {

        Access<String> access = new GetResourceAccess<String>("https://mama.admore.cc");

        String result = access.access();

        System.out.println(result);

    }

}
