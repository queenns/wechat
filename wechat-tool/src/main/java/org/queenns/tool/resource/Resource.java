package org.queenns.tool.resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

/**
 * Created by lxj on 18-2-27
 * <p>
 * 用于从底层资源的实际类型中抽象出来的资源描述符的接口，例如文件或类路径资源。
 * <p>
 * <reference source Spring>
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

    boolean exists();

    boolean isReadable();

    boolean isOpen();

    URL getURL() throws IOException;

    URI getURI() throws IOException;

    File getFile() throws IOException;

    long contentLength() throws IOException;

    long lastModified() throws IOException;

    String getFilename();

}
