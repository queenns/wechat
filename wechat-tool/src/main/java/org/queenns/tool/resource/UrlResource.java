package org.queenns.tool.resource;

import org.queenns.tool.util.ObjectUtil;
import org.queenns.tool.util.ResourceUtil;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;

/**
 * Created by lxj on 18-4-3
 */
public class UrlResource extends FileResource {

    private final URI uri;

    private final URL url;

    public UrlResource(URI uri) throws MalformedURLException {

        this.uri = uri;

        this.url = uri.toURL();

    }

    public UrlResource(URL url) {

        this.uri = null;

        this.url = url;

    }

    public UrlResource(String url) throws MalformedURLException {

        this.uri = null;

        this.url = new URL(url);

    }

    @Override
    public InputStream getInputStream() throws IOException {

        URLConnection urlConnection = this.url.openConnection();

        ResourceUtil.necessaryCache(urlConnection);

        try {

            return urlConnection.getInputStream();

        } catch (IOException e) {

            if (urlConnection instanceof HttpURLConnection)

                ((HttpsURLConnection) urlConnection).disconnect();

            throw e;

        }

    }

    @Override
    public URL getURL() throws IOException {

        return this.url;

    }

    @Override
    public URI getURI() throws IOException {

        if (!ObjectUtil.isEmpty(this.uri))

            return this.uri;

        else

            return super.getURI();

    }

    @Override
    public File getFile() throws IOException {

        return !ObjectUtil.isEmpty(this.uri) ? super.getFile(this.uri) : super.getFile();

    }

    @Override
    public String getFilename() {

        return new File(this.url.getFile()).getName();

    }

}
