package com.jxys.user.exception;

import com.jxys.base.exception.ServiceException;

public class PasswordErrorException extends ServiceException {
    public PasswordErrorException() {
        super(10400, "密码错误");
    }
}