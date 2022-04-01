package com.jxys.user.controller;

import com.jxys.base.entity.BaseController;
import com.jxys.base.entity.Result;
import com.jxys.data.SignInData;
import com.jxys.data.SignUpData;
import com.jxys.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    /**
     * 登陆接口
     */
    @PostMapping("/signIn")
    public Result signIn(@RequestBody @Validated SignInData data) {
        // 使用SpringValidation校验数据
        return success("登录成功", userService.signIn(data));
    }

    /**
     * 注册接口
     */
    @PostMapping("/signUp")
    public Result signUp(@RequestBody @Validated SignUpData data) {
        // 使用SpringValidation校验数据
        String result = userService.signUp(data);
        return success(result);
    }


    /**
     * 根据Id查看单个用户的信息
     */
    @GetMapping("/{userId}")
    public Result get(@PathVariable("userId") String userId) {
        return success("查询成功", userService.getById(userId));
    }

    /**
     * 查看用户列表
     */
    @GetMapping("/list")
    public Result list() {
        // 该接口需要检测用户是否登陆
        return success("查询成功", userService.list());
    }
}