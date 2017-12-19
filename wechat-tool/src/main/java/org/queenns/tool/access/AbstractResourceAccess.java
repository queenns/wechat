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
    protected DisposeTransform disposeTransform;

    /**
     * timeout for connection
     */
    protected Integer CONNECTION_TIMEOUT = 10000;

    /**
     * timeout for waiting for data
     */
    protected Integer WAITING_DATA_TIMEOUT = 10000;


    @Override
    public T access() {

        logger.info("access url : {}", url);

        HttpClient httpClient = createClient();

        HttpMethod httpMethod = createMethod();

        httpClient.getHttpConnectionManager().getParams().setSoTimeout(CONNECTION_TIMEOUT);

        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(WAITING_DATA_TIMEOUT);

        try {

            httpClient.executeMethod(httpMethod);

            return (T) disposeTransform.transformDispose();

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

                logger.error("http method type not found : " + methodType);

                return new GetMethod(url);

        }

    }

}
