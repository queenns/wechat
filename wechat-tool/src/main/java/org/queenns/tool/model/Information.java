package org.queenns.tool.model;

import org.queenns.tool.access.MethodType;

/**
 * Created by lxj on 18-1-12
 */
public class Information {

    private String id;

    private MethodType methodType;

    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MethodType getMethodType() {
        return methodType;
    }

    public void setMethodType(MethodType methodType) {
        this.methodType = methodType;
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
                "id='" + id + '\'' +
                ", methodType=" + methodType +
                ", url='" + url + '\'' +
                '}';
    }

}
