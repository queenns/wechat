package org.queenns.tool.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * Created by lxj on 17-12-27
 */
public class ObjectUtil {

    public static boolean isEmpty(Object obj) {

        if (obj == null) return true;

        else if (obj.getClass().isArray()) return Array.getLength(obj) == 0;

        else if (obj instanceof CharSequence) return ((CharSequence) obj).length() == 0;

        else if (obj instanceof Collection) return ((Collection) obj).isEmpty();

        else if (obj instanceof Map) return ((Map) obj).isEmpty();

        return false;

    }

}
