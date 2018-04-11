package org.queenns.tool.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by lxj on 18-3-7
 */
public abstract class PropertiesUtil {

    private static final String XML_FILE_EXTENSION = ".xml";

    public static Properties loadProperties(String location) throws IOException {

        return loadProperties(location, null);

    }

    public static Properties loadProperties(String location, ClassLoader classLoader) throws IOException {

        AssertUtil.empty(StringUtil.isEmpty(location), "Properties location must not be null");

        ClassLoader loaderClassLoader = !ObjectUtil.isEmpty(classLoader) ? classLoader : ClassUtil.getDefaultClassLoader();

        Enumeration<URL> urls = !ObjectUtil.isEmpty(loaderClassLoader) ? loaderClassLoader.getResources(location) : ClassLoader.getSystemResources(location);

        Properties properties = new Properties();

        while (urls.hasMoreElements()) {

            URL url = urls.nextElement();

            URLConnection urlConnection = url.openConnection();

            ResourceUtil.necessaryCache(urlConnection);

            try (InputStream inputStream = urlConnection.getInputStream()) {

                if (location.endsWith(XML_FILE_EXTENSION))

                    properties.loadFromXML(inputStream);

                else

                    properties.load(inputStream);

            }

        }

        return properties;

    }

}
