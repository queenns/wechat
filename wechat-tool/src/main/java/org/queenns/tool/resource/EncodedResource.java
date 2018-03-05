package org.queenns.tool.resource;

import org.queenns.tool.util.ObjectUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

/**
 * Created by lxj on 18-3-5
 */
public class EncodedResource implements InputStreamSource {

    private final Resource resource;

    private final String encoding;

    private final Charset charset;

    public EncodedResource(Resource resource) {

        this(resource, null, null);

    }

    public EncodedResource(Resource resource, String encoding) {

        this(resource, encoding, null);

    }

    public EncodedResource(Resource resource, Charset charset) {

        this(resource, null, charset);

    }

    private EncodedResource(Resource resource, String encoding, Charset charset) {

        if (ObjectUtil.isEmpty(resource)) throw new IllegalArgumentException("Resource must not be null");

        this.resource = resource;

        this.encoding = encoding;

        this.charset = charset;

    }

    public Reader getReader() throws IOException {

        if (!ObjectUtil.isEmpty(this.charset))

            return new InputStreamReader(this.resource.getInputStream(), this.charset);

        else if (!ObjectUtil.isEmpty(this.encoding))

            return new InputStreamReader(this.resource.getInputStream(), this.encoding);

        else

            return new InputStreamReader(this.resource.getInputStream());

    }

    @Override
    public InputStream getInputStream() throws IOException {

        return this.resource.getInputStream();

    }

    public final Resource getResource() {
        return resource;
    }

    public final String getEncoding() {
        return encoding;
    }

    public final Charset getCharset() {
        return charset;
    }

}
