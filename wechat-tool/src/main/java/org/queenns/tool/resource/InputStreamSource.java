package org.queenns.tool.resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lxj on 18-3-5
 */
public interface InputStreamSource {

    InputStream getInputStream() throws IOException;

}
