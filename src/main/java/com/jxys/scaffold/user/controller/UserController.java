package com.jxys.scaffold.user.controller;

import com.jxys.scaffold.auth.annotation.Auth;
import com.jxys.scaffold.base.entity.Result;
import com.jxys.scaffold.user.data.SignInData;
import com.jxys.scaffold.user.data.SignUpData;
import com.jxys.scaffold.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登陆接口
     */
    @PostMapping("/signIn")
    public Result signIn(@RequestBody @Validated SignInData data) {
        // 使用SpringValidation校验数据
        return new Result().success(userService.signIn(data));
    }

    /**
     * 注册接口
     */
    @PostMapping("/signUp")
    public Result signUp(@RequestBody @Validated SignUpData data) {
        // 使用SpringValidation校验数据
        String result = userService.signUp(data);
        return new Result().success(result);
    }

    /**
     * 查看当前用户的Id
     */
    @GetMapping
    public Result get(@Auth(required = false) Long userId) {
        // 该接口可以不登陆，未登陆时返回的结果为空，登陆时会返回当前登陆用户的Id
        return new Result().success(userId);
    }

    /**
     * 根据Id查看单个用户的信息
     */
    @GetMapping("/{userId}")
    public Result get(@PathVariable("userId") String userId) {
        return new Result().success(userService.getById(userId));
    }

    /**
     * 查看用户列表
     */
    @GetMapping("/list")
    public Result list() {
        // 该接口需要检测用户是否登陆
        return new Result().success(userService.list());
    }
}