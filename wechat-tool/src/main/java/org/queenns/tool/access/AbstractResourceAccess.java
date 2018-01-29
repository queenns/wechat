package org.queenns.tool.access;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.queenns.tool.exception.AccessException;
import org.queenns.tool.transform.dispose.DisposeTransform;
import org.queenns.tool.transform.dispose.JsonDisposeTransform;
import org.queenns.tool.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

/**
 * Created by lxj on 17-12-19
 */
public abstract class AbstractResourceAccess<T> implements Access<T> {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * result class type
     */
    private Class<T> clazz;

    /**
     * access the resource url
     */
    private String url;

    /**
     * access method type
     */
    private MethodType methodType;

    /**
     * data real dispose
     */
    private DisposeTransform<T> disposeTransform;

    /**
     * timeout for connection
     */
    private Integer CONNECTION_TIMEOUT = 10000;

    /**
     * timeout for waiting for data
     */
    private Integer WAITING_DATA_TIMEOUT = 10000;

    AbstractResourceAccess(MethodType methodType, Class<T> clazz) {

        this.clazz = clazz;

        this.methodType = methodType;

        this.disposeTransform = defaultDisposeTransform();

    }

    AbstractResourceAccess(String url, MethodType methodType, Class<T> clazz) {

        this.url = url;

        this.clazz = clazz;

        this.methodType = methodType;

        this.disposeTransform = defaultDisposeTransform();

    }

    AbstractResourceAccess(MethodType methodType, DisposeTransform<T> disposeTransform, Class<T> clazz) {

        this.clazz = clazz;

        this.methodType = methodType;

        this.disposeTransform = disposeTransform;

    }

    AbstractResourceAccess(String url, MethodType methodType){

        initClazz();

        this.url = url;

        this.methodType = methodType;

        this.disposeTransform = defaultDisposeTransform();

    }

    /**
     * 只有当显式地声明具有具体类的泛型类型时，才会从派生类调用这个调用
     *
     * @param url              url
     * @param methodType       methodType
     * @param disposeTransform disposeTransform
     */
    AbstractResourceAccess(String url, MethodType methodType, DisposeTransform<T> disposeTransform) {

        initClazz();

        this.url = url;

        this.methodType = methodType;

        this.disposeTransform = disposeTransform;

    }

    /**
     * 只有当显式地声明具有具体类的泛型类型时，才会从派生类调用这个调用
     *
     * @param url                  url
     * @param methodType           methodType
     * @param disposeTransform     disposeTransform
     * @param CONNECTION_TIMEOUT   CONNECTION_TIMEOUT
     * @param WAITING_DATA_TIMEOUT WAITING_DATA_TIMEOUT
     */
    AbstractResourceAccess(String url, MethodType methodType, DisposeTransform<T> disposeTransform, Integer CONNECTION_TIMEOUT, Integer WAITING_DATA_TIMEOUT) {

        initClazz();

        this.url = url;

        this.methodType = methodType;

        this.disposeTransform = disposeTransform;

        this.CONNECTION_TIMEOUT = CONNECTION_TIMEOUT;

        this.WAITING_DATA_TIMEOUT = WAITING_DATA_TIMEOUT;

    }

    @SuppressWarnings("unchecked")
    private void initClazz() {

        this.clazz = ((Class<T>) (((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]));

    }

    @Override
    public T access() throws IOException {

        if (StringUtil.isEmpty(url)) throw new AccessException("access url not found is null");

        logger.info("access url : {}", url);

        HttpClient httpClient = createClient();

        HttpMethod httpMethod = createMethod();

        httpClient.getHttpConnectionManager().getParams().setSoTimeout(CONNECTION_TIMEOUT);

        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(WAITING_DATA_TIMEOUT);

        try {

            long startTime = System.currentTimeMillis();

            httpClient.executeMethod(httpMethod);

            long finishTime = System.currentTimeMillis();

            logger.info("access continued time {} mm", finishTime - startTime);

            return disposeTransform.disposeTransform(httpMethod);

        } finally {

            httpMethod.releaseConnection();

        }

    }

    private DisposeTransform<T> defaultDisposeTransform() {

        return new JsonDisposeTransform<>(getClazz());

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

                throw new AccessException("http method type not found : " + methodType);

        }

    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
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
