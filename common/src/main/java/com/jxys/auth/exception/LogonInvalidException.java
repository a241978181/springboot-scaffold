package com.jxys.auth.exception;

import com.jxys.base.exception.ServiceException;

/**
 * @Author 李建
 * @Date 2020/11/20 15:17
 * @Version 1.0
 **/
public class LogonInvalidException extends ServiceException {
    public LogonInvalidException() {
        super("登录已过期，请重新登录。", 401);
    }
}
