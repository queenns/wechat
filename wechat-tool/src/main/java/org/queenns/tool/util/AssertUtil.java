package org.queenns.tool.util;

/**
 * Created by lxj on 18-4-11
 */
public abstract class AssertUtil {

    public static void empty(Boolean empty, String exception) {

        if (!empty) return;

        throw new IllegalArgumentException(exception);

    }

}
