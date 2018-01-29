package org.queenns.tool.transform.dispose;

import org.apache.commons.httpclient.HttpMethod;
import org.queenns.tool.util.JacksonUtil;

import java.io.IOException;

/**
 * Created by lxj on 18-1-12
 */
public class JsonDisposeTransform<T> implements DisposeTransform<T> {

    private Class<T> clazz;

    public JsonDisposeTransform(Class<T> clazz) {

        this.clazz = clazz;

    }

    @Override
    public T disposeTransform(HttpMethod httpMethod) throws IOException {

        return JacksonUtil.parseJsonToObj(httpMethod.getResponseBodyAsString(), getClazz());

    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

}
