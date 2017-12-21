package org.queenns.tool.access;

/**
 * Created by lxj on 17-12-19
 */
public class GetResourceAccess<T> extends AbstractResourceAccess<T> {

    public GetResourceAccess(String url) {

        super(url, MethodType.GET, new DefaultDisposeTransform<T>());

    }

}
