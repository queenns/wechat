package org.queenns.tool.access;

import org.apache.commons.httpclient.HttpMethod;
import org.queenns.tool.util.JacksonUtil;

import java.io.IOException;

/**
 * Created by lxj on 17-12-21
 */
public class DefaultDisposeTransform<T> implements DisposeTransform<T> {

    private Class<?> clazz;

    public DefaultDisposeTransform(Class<?> clazz) {

        this.clazz = clazz;

    }

    @Override
    public T disposeTransform(HttpMethod httpMethod) throws IOException {

        return JacksonUtil.parseJsonToObj(httpMethod.getResponseBodyAsString(), this.clazz);

    }

}
