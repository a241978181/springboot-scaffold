package com.jxys.auth.exception;

import com.jxys.base.exception.ServiceException;

/**
 * 未认证异常
 *
 * @author 李建
 * @since 2020年11月20日14:37:36
 */
public class UnauthorizedException extends ServiceException {
    public UnauthorizedException() {
        super("未授权登录", 401);
    }
}
