package org.queenns.tool.util;

import java.util.Collection;
import java.util.Map;

/**
 * Created by lxj on 17-12-27
 */
public class CollectionUtil {

    public static boolean isEmpty(Collection<?> collection) {

        return (collection == null || collection.isEmpty());

    }

    public static boolean isEmpty(Map<?, ?> map) {

        return (map == null || map.isEmpty());

    }

}
