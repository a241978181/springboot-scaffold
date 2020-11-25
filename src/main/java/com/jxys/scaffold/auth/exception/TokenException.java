package com.jxys.scaffold.auth.exception;

/**
 * Token异常
 *
 * @author 李建
 * @since 2020年11月20日14:36:20
 */
public class TokenException extends RuntimeException {
    public TokenException() {
        super("无效token");
    }
}
