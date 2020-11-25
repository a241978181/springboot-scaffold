package com.jxys.scaffold.user.exception;

import com.jxys.scaffold.base.exception.ServiceException;

public class PasswordErrorException extends ServiceException {
    public PasswordErrorException() {
        super(10400, "密码错误");
    }
}