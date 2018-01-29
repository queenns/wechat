package org.queenns.tool.transform.dispose;

import org.apache.commons.httpclient.HttpMethod;

import java.io.IOException;

/**
 * Created by lxj on 17-12-19
 */
public interface DisposeTransform<T> {

    T disposeTransform(HttpMethod httpMethod) throws IOException;

}
