package org.queenns.tool.resource;

import org.queenns.tool.util.ClassUtil;
import org.queenns.tool.util.ObjectUtil;
import org.queenns.tool.util.StringUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by lxj on 18-2-27
 */
public class ClassPathResource extends FileResource {

    private String classPath;

    private ClassLoader classLoader;

    public ClassPathResource(String classPath) {

        this(classPath, null);

    }

    public ClassPathResource(String classPath, ClassLoader classLoader) {

        if (StringUtil.isEmpty(classPath)) throw new IllegalArgumentException(classPath);

        this.classPath = classPath;

        this.classLoader = (classLoader != null ? classLoader : ClassUtil.getDefaultClassLoader());

    }

    protected URL resolveURL() {

        return !ObjectUtil.isEmpty(classLoader) ? this.classLoader.getResource(this.classPath) : ClassLoader.getSystemResource(this.classPath);

    }

    @Override
    public InputStream getInputStream() throws IOException {

        InputStream inputStream = !ObjectUtil.isEmpty(classLoader) ? classLoader.getResourceAsStream(this.classPath) : ClassLoader.getSystemResourceAsStream(this.classPath);

        if (ObjectUtil.isEmpty(inputStream)) throw new FileNotFoundException(this.getClass().getName() + " cannot be opened because it does not exist");

        return inputStream;

    }

    @Override
    public boolean exists() {

        return (!ObjectUtil.isEmpty(resolveURL()));

    }

    @Override
    public URL getURL() throws IOException {

        URL url = resolveURL();

        if (ObjectUtil.isEmpty(url)) throw new FileNotFoundException(this.getClass().getName() + "cannot be resolved to URL because it does not exist");

        return url;

    }

    @Override
    public String getFilename() {

        int lastIndex = classPath.lastIndexOf("/");

        return (lastIndex != -1 ? classPath.substring(lastIndex + 1) : classPath);

    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

}
