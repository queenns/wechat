package org.queenns.tool.access;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lxj on 17-12-19
 */
public abstract class WechatResourceAccess<T> implements Access<T> {

    private static Logger logger = LoggerFactory.getLogger(WechatResourceAccess.class);

    private String url;

    private MethodType methodType;

    public T resourceAccess(String url, MethodType methodType) {

        logger.info("access url : {}", url);

        HttpClient httpClient = createClient();

        HttpMethod httpMethod = createMethod(url, methodType);

        access(httpClient, httpMethod);

        return null;

    }

    private static void access(HttpClient httpClient, HttpMethod httpMethod) {


    }

    private static HttpMethod createMethod(String url, MethodType methodType) {

        switch (methodType) {

            case GET:
                return new GetMethod(url);

            case POST:
                return new PostMethod(url);

            default:

                try {

                    throw new Exception("http method type not found[" + methodType + "]");

                } catch (Exception e) {

                    e.printStackTrace();

                    return null;

                }

        }

    }

    private static HttpClient createClient() {

        return new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));

    }


}
