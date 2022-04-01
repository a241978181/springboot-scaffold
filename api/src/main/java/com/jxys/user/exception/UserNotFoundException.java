package com.jxys.user.exception;

import com.jxys.base.exception.ServiceException;

public class UserNotFoundException extends ServiceException {
    public UserNotFoundException() {
        super(10404, "用户不存在");
    }
}