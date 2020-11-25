package com.jxys.scaffold.auth.exception;

import com.jxys.scaffold.base.exception.ServiceException;

/**
 * @Author 李建
 * @Date 2020/11/20 15:17
 * @Version 1.0
 **/
public class LogonInvalidException extends ServiceException {
    public LogonInvalidException() {
        super("401","登录已过期，请重新登录。");
    }
}
