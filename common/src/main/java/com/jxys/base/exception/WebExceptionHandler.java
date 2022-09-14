package com.jxys.base.exception;

import com.jxys.base.entity.BaseController;
import com.jxys.base.entity.Result;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * validate校验 异常统一捕捉处理类
 *
 * @Author 李建
 * @Date 2022/8/12
 */
@ControllerAdvice
public class WebExceptionHandler extends BaseController {

    /**
     * 处理请求参数格式错误 @RequestBody上使用@Valid 实体上使用@NotNull等，验证失败后抛出的异常是MethodArgumentNotValidException异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String message = "【" + Objects.requireNonNull(e.getBindingResult().getFieldError()).getField() + "】" + e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return fail(message, 400);
    }

    /**
     * 处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result BindExceptionHandler(BindException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return fail(message, 400);
    }

    /**
     * 处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是ConstraintViolationException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Result ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return fail(message, 400);
    }

    /**
     * 参数格式异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Result HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        return fail("参数格式异常", 400);
    }


}