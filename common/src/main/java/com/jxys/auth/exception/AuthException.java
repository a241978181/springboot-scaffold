package com.jxys.auth.exception;

import com.jxys.base.exception.ServiceException;

/**
 * 认证异常
 *
 * @author 李建
 * @since 2020年11月20日14:37:14
 */
public class AuthException extends ServiceException {
    public AuthException() {
        super("身份验证失败", 403);
    }
}
