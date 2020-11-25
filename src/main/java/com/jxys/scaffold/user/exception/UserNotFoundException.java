package com.jxys.scaffold.user.exception;

import com.jxys.scaffold.base.exception.ServiceException;

public class UserNotFoundException extends ServiceException {
    public UserNotFoundException() {
        super(10404, "用户不存在");
    }
}