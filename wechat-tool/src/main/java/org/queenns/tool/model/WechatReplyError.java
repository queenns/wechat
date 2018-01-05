package org.queenns.tool.model;

import java.util.List;

/**
 * Created by lxj on 17-12-21
 * <p>
 * 微信接口响应错误码
 */
public class WechatReplyError {

    private List<Error> errors;

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "WechatReplyError{" +
                "errors=" + errors +
                '}';
    }

    private static class Error {

        private Integer code;

        private String msg;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "WechatReplyError{" +
                    "code=" + code +
                    ", msg='" + msg + '\'' +
                    '}';
        }

    }

}
