package org.queenns.tool.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by lxj on 18-3-5
 */
public abstract class ResourceUtil {

    /**
     * URL protocol for a file in the file system: "file"
     */
    public static final String URL_PROTOCOL_FILE = "file";

    /** URL protocol for an entry from a jar file: "jar" */
    public static final String URL_PROTOCOL_JAR = "jar";

    /** URL protocol for an entry from a zip file: "zip" */
    public static final String URL_PROTOCOL_ZIP = "zip";

    public static File getFile(URL url) throws FileNotFoundException {

        if (ObjectUtil.isEmpty(url)) throw new IllegalArgumentException("resource url must not be null");

        if (!URL_PROTOCOL_FILE.equals(url.getProtocol()))
            throw new FileNotFoundException(url.getProtocol() + "not equals" + URL_PROTOCOL_FILE);

        try {

            return new File(toURI(url).getSchemeSpecificPart());

        } catch (URISyntaxException ex) {

            return new File(url.getFile());

        }

    }

    public static URI toURI(URL url) throws URISyntaxException {

        return toURI(url.toString());

    }

    public static URI toURI(String location) throws URISyntaxException {

        return new URI(StringUtil.replace(location, " ", "%20"));

    }

    public static boolean isFileUrl(URL url) {

        return URL_PROTOCOL_FILE.equals(url.getProtocol());

    }

    public static boolean isJarURL(URL url) {

        String protocol = url.getProtocol();

        return (URL_PROTOCOL_JAR.equals(protocol) || URL_PROTOCOL_ZIP.equals(protocol) );

    }

    /**
     * Java Network Launching Protocol
     *
     * @param urlConnection URLConnection
     */
    public static void necessaryCache(URLConnection urlConnection) {

        urlConnection.setUseCaches(urlConnection.getClass().getSimpleName().startsWith("JNLP"));

    }

}
