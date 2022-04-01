package com.jxys.base.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 返回结果实体类
 *
 * @author 李建
 * @update 2019/1/7
 * @since 2018/12/6
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private int code;
    private String message;
    private T data;

    private Result setResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        return this;
    }

    public Result success() {
        return setResult(200, "Success", null);
    }

    public Result success(String message) {
        return setResult(200, message, null);
    }

    public Result success(T data) {
        return setResult(200, "Success", data);
    }

    public Result success(T data, String message) {
        return setResult(200, message, data);
    }

    public Result fail(T data, String message) {
        return setResult(500, message, data);
    }

    public Result fail(String message) {
        return setResult(500, message, null);
    }

    public Result fail(T data, String message, int code) {
        return setResult(code, message, data);
    }

    public Result fail(String message, int code) {
        return setResult(code, message, null);
    }
}