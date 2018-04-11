package org.queenns.tool.resource;

import org.queenns.tool.util.AssertUtil;
import org.queenns.tool.util.ObjectUtil;
import org.queenns.tool.util.StringUtil;

import java.io.*;
import java.net.URI;
import java.net.URL;

/**
 * Created by lxj on 18-4-11
 */
public class FileSystemResource extends AbstractResource implements WritableResource {

    /**
     * File
     */
    private final File file;

    /**
     * 文件路径
     */
    private final String path;

    public FileSystemResource(File file) {

        AssertUtil.empty(ObjectUtil.isEmpty(file), "file must not be null");

        this.path = file.getPath();

        this.file = file;

    }

    public FileSystemResource(String path) {

        AssertUtil.empty(StringUtil.isEmpty(path), "path must not be null");

        this.path = path;

        this.file = new File(path);

    }

    public final String getPath() {

        return this.path;

    }

    @Override
    public boolean isReadable() {

        return (this.file.canRead() && !this.file.isDirectory());

    }

    @Override
    public InputStream getInputStream() throws IOException {

        return new FileInputStream(this.file);

    }

    @Override
    public boolean isWritable() {

        return (this.file.canWrite() && !this.file.isDirectory());

    }

    @Override
    public OutputStream getOutputStream() throws IOException {

        return new FileOutputStream(this.file);

    }

    @Override
    public URL getURL() throws IOException {

        return getURI().toURL();

    }

    @Override
    public URI getURI() throws IOException {

        return this.file.toURI();

    }

    @Override
    public File getFile() throws IOException {

        return this.file;

    }

    @Override
    public long contentLength() throws IOException {

        return this.file.length();

    }

    @Override
    public String getFilename() {

        return this.file.getName();

    }

}
