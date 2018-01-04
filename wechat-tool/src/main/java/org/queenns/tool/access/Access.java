package org.queenns.tool.access;

import java.io.IOException;

/**
 * Created by lxj on 17-12-19
 */
public interface Access<T> {

    T access() throws IOException;

}
