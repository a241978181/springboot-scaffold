package com.jxys.scaffold.auth.enums;

/**
 * Auth模块Token类型枚举
 *
 * @author 李建
 * @since 2020年11月20日14:37:02
 */
public enum TokenSubject {
    ACCESS("access"), REFRESH("refresh");

    private final String type;

    TokenSubject(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }
}
