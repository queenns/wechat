package org.queenns.tool.access;

import org.apache.commons.httpclient.HttpMethod;

import java.io.IOException;

/**
 * Created by lxj on 17-12-21
 */
public class DefaultDisposeTransform<T> implements DisposeTransform<T> {

    @Override
    public T disposeTransform(HttpMethod httpMethod) throws IOException {

        return (T) httpMethod.getResponseBodyAsString();

    }

}
