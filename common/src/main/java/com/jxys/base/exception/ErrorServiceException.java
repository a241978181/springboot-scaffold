package com.jxys.base.exception;

/**
 * @Author 李建
 * @Date 2020/11/20 14:53
 * @Version 1.0
 **/
public class ErrorServiceException extends ServiceException {
    public ErrorServiceException() {
        super("500","程序出现未知错误。");
    }
    public ErrorServiceException(String errorMessage) {
        super("500",errorMessage);
    }
}
