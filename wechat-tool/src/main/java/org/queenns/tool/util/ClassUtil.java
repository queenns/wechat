package org.queenns.tool.util;

/**
 * Created by lxj on 18-2-27
 */
public class ClassUtil {

    public static ClassLoader getDefaultClassLoader() {

        ClassLoader classLoader = null;

        try {

            classLoader = Thread.currentThread().getContextClassLoader();

        } catch (Throwable ignore) {

        }

        if (classLoader == null) {

            classLoader = ClassUtil.class.getClassLoader();

            if (classLoader == null) {

                try {

                    classLoader = ClassLoader.getSystemClassLoader();

                } catch (Throwable ignore) {

                }

            }

        }

        return classLoader;

    }

}
