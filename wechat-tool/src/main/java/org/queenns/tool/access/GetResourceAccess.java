package org.queenns.tool.access;

/**
 * Created by lxj on 17-12-19
 */
public class GetResourceAccess<T> extends AbstractResourceAccess<T> {

    public GetResourceAccess(Class<T> clazz) {

        super(MethodType.GET, clazz);

    }

    public GetResourceAccess(String url, Class<T> clazz) {

        super(url, MethodType.GET, clazz);

    }

    public GetResourceAccess(DisposeTransform<T> disposeTransform, Class<T> clazz) {

        super(MethodType.GET, disposeTransform, clazz);

    }

}
