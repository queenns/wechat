package org.queenns.tool.access;

import org.queenns.tool.transform.dispose.DisposeTransform;

/**
 * Created by lxj on 17-12-19
 */
public class GetResourceAccess<T> extends AbstractResourceAccess<T> {

    public GetResourceAccess(String url) {

        super(url, Method.GET);

    }

    public GetResourceAccess(Class<T> clazz) {

        super(Method.GET, clazz);

    }

    public GetResourceAccess(String url, Class<T> clazz) {

        super(url, Method.GET, clazz);

    }

    public GetResourceAccess(DisposeTransform<T> disposeTransform, Class<T> clazz) {

        super(Method.GET, disposeTransform, clazz);

    }

}
