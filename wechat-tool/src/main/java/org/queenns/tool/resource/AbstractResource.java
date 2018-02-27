package org.queenns.tool.resource;

import org.queenns.tool.util.ObjectUtil;
import org.queenns.tool.util.StringUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by lxj on 18-2-27
 * <p>
 * <reference source Spring>
 */
public abstract class AbstractResource implements Resource {

    public AbstractResource() {
    }

    @Override
    public InputStream getInputStream() throws IOException {

        return null;
    }

    @Override
    public boolean exists() {

        try {

            return getFile().exists();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return false;
    }

    @Override
    public boolean isReadable() {

        return false;

    }

    @Override
    public boolean isOpen() {

        return false;

    }

    @Override
    public URL getURL() throws IOException {

        throw new FileNotFoundException(this.getClass().getName() + " no specific definition of access");

    }

    @Override
    public URI getURI() throws IOException {

        URL url = getURL();

        try {

            String uri = StringUtil.replace(url.toString(), " ", "%20");

            return new URI(uri);

        } catch (URISyntaxException e) {

            throw new IOException("Invalid URI [" + url + "]", e);

        }

    }

    @Override
    public File getFile() throws IOException {

        throw new FileNotFoundException(this.getClass().getName() + " no specific definition of access");

    }

    @Override
    public long contentLength() throws IOException {

        try(InputStream inputStream = getInputStream()){

            if (ObjectUtil.isEmpty(inputStream)) throw new IllegalStateException("Resource InputStream must not be null");

            long size = 0;

            byte[] buf = new byte[255];

            int read;

            while ((read = inputStream.read(buf)) != -1) size += read;

            return size;

        }

    }

    @Override
    public long lastModified() throws IOException {

        long lastModified = getFile().lastModified();

        if (lastModified == 0L) throw new FileNotFoundException(this.getClass().getName() + "cannot be resolved last-modified");

        return lastModified;

    }

    @Override
    public String getFilename(){

        return null;

    }

}
