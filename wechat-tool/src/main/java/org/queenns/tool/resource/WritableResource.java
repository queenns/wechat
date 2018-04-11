package org.queenns.tool.resource;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by lxj on 18-4-11
 */
public interface WritableResource extends Resource {

    /**
     * 返回该内容是否可以修改
     * true 注意实际内容编写可能在尝试时仍然失败
     * false 指示不能修改资源
     *
     * @return boolean
     */
    boolean isWritable();

    /**
     * 返回一个底层资源的 {@link OutputStream}
     *
     * @return {@link OutputStream}
     * @throws IOException 当这个流不能打开时抛出
     */
    OutputStream getOutputStream() throws IOException;

}
