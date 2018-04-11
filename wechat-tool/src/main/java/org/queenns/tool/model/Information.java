package org.queenns.tool.model;

import org.queenns.tool.access.Method;

/**
 * Created by lxj on 18-1-12
 */
public class Information {

    private String id;

    private Method method;

    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Information{" +
                "id=" + id +
                ", method=" + method +
                ", url=" + url +
                '}';
    }

}
