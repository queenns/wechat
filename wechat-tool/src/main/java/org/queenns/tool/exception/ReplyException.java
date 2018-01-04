package org.queenns.tool.exception;

/**
 * Created by lxj on 17-12-21
 */
public class ReplyException extends Exception {

    static final long serialVersionUID = -1722561246691552664L;

    public ReplyException(Integer code, String error) {

        super("code[" + code + "]error[" + error + "]");

    }

}
