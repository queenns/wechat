package org.queenns.tool.access;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by lxj on 17-12-19
 */
public abstract class AbstractResourceAccess<T> implements Access<T> {

    private static Logger logger = LoggerFactory.getLogger(AbstractResourceAccess.class);

    /**
     * access the resource url
     */
    protected String url;

    /**
     * access method type
     */
    protected MethodType methodType;

    /**
     * data real dispose
     */
    protected DisposeTransform<T> disposeTransform;

    /**
     * timeout for connection
     */
    protected Integer CONNECTION_TIMEOUT = 10000;

    /**
     * timeout for waiting for data
     */
    protected Integer WAITING_DATA_TIMEOUT = 10000;

    protected AbstractResourceAccess(String url, MethodType methodType, DisposeTransform<T> disposeTransform) {

        this.url = url;

        this.methodType = methodType;

        this.disposeTransform = disposeTransform;

    }

    @Override
    public T access() {

        logger.info("access url : {}", url);

        HttpClient httpClient = createClient();

        HttpMethod httpMethod = createMethod();

        httpClient.getHttpConnectionManager().getParams().setSoTimeout(CONNECTION_TIMEOUT);

        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(WAITING_DATA_TIMEOUT);

        try {

            httpClient.executeMethod(httpMethod);

            return disposeTransform.disposeTransform(httpMethod);

        } catch (IOException e) {

            e.printStackTrace();

            return null;

        } finally {

            httpMethod.releaseConnection();

        }

    }

    private HttpClient createClient() {

        return new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));

    }

    private HttpMethod createMethod() {

        switch (methodType) {

            case GET:
                return new GetMethod(url);

            case POST:
                return new PostMethod(url);

            default:

                throw new NullPointerException("http method type not found : " + methodType);

        }

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MethodType getMethodType() {
        return methodType;
    }

    public void setMethodType(MethodType methodType) {
        this.methodType = methodType;
    }

    public DisposeTransform<T> getDisposeTransform() {
        return disposeTransform;
    }

    public void setDisposeTransform(DisposeTransform<T> disposeTransform) {
        this.disposeTransform = disposeTransform;
    }

    public Integer getCONNECTION_TIMEOUT() {
        return CONNECTION_TIMEOUT;
    }

    public void setCONNECTION_TIMEOUT(Integer CONNECTION_TIMEOUT) {
        this.CONNECTION_TIMEOUT = CONNECTION_TIMEOUT;
    }

    public Integer getWAITING_DATA_TIMEOUT() {
        return WAITING_DATA_TIMEOUT;
    }

    public void setWAITING_DATA_TIMEOUT(Integer WAITING_DATA_TIMEOUT) {
        this.WAITING_DATA_TIMEOUT = WAITING_DATA_TIMEOUT;
    }

}
