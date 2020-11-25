package com.jxys.scaffold.user.exception;

import com.jxys.scaffold.base.exception.ServiceException;

/**
 * 已登录验证报错
 * @Author 李建
 * @Date 2020/11/20 14:47
 * @Version 1.0
 **/

public class LoggedNotException extends ServiceException {
    public LoggedNotException() {
        super("10402","您已登录，无法再次登录。");
    }
}
