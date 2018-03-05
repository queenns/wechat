package org.queenns.tool.resource;

import org.queenns.tool.util.ObjectUtil;
import org.queenns.tool.util.ResourceUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by lxj on 18-3-5
 */
public abstract class FileResource extends AbstractResource {

    @Override
    public File getFile() throws IOException {

        URL url = getURL();

        return ResourceUtil.getFile(url);

    }

    @Override
    public boolean exists() {

        try {

            URL url = getURL();

            if (ResourceUtil.isFileUrl(url)) {

                return getFile().exists();

            } else {

                URLConnection urlConnection = url.openConnection();

                defineConnection(urlConnection);

                HttpURLConnection httpUrlConnection = (urlConnection instanceof HttpURLConnection ? (HttpURLConnection) urlConnection : null);

                if (!ObjectUtil.isEmpty(httpUrlConnection)) {

                    int code = httpUrlConnection.getResponseCode();

                    if (code == HttpURLConnection.HTTP_OK) return true;

                    else if (code == HttpURLConnection.HTTP_NOT_FOUND) return false;

                }

                if (urlConnection.getContentLength() >= 0) return true;

                if (httpUrlConnection != null) {

                    httpUrlConnection.disconnect();

                    return false;

                } else {

                    InputStream is = getInputStream();

                    is.close();

                    return true;

                }

            }

        } catch (IOException ex) {

            return false;

        }

    }

    @Override
    public boolean isReadable() {

        try {

            URL url = getURL();

            if (ResourceUtil.isFileUrl(url)) {

                File file = getFile();

                return (file.canRead() && !file.isDirectory());

            } else {

                return true;

            }

        } catch (IOException ex) {

            return false;

        }

    }

    @Override
    public long contentLength() throws IOException {

        URL url = getURL();

        if (ResourceUtil.isFileUrl(url)) {

            return getFile().length();

        } else {

            URLConnection urlConnection = url.openConnection();

            defineConnection(urlConnection);

            return urlConnection.getContentLength();

        }

    }

    @Override
    public long lastModified() throws IOException {

        URL url = getURL();

        if (ResourceUtil.isFileUrl(url) || ResourceUtil.isJarURL(url)) {

            return super.lastModified();

        } else {

            URLConnection urlConnection = url.openConnection();

            defineConnection(urlConnection);

            return urlConnection.getLastModified();

        }

    }

    protected void defineConnection(URLConnection urlConnection) throws IOException {

        ResourceUtil.necessaryCache(urlConnection);

        if (urlConnection instanceof HttpURLConnection) defineConnection((HttpURLConnection) urlConnection);

    }

    protected void defineConnection(HttpURLConnection httpUrlConnection) throws IOException {

        httpUrlConnection.setRequestMethod("HEAD");

    }

}
