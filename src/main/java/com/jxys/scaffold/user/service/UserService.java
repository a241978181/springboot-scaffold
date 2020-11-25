package com.jxys.scaffold.user.service;

import com.jxys.scaffold.user.data.SignInData;
import com.jxys.scaffold.user.data.SignUpData;
import com.jxys.scaffold.user.entity.User;
import com.jxys.scaffold.user.view.SignInView;

import java.util.List;

public interface UserService{
    SignInView signIn(SignInData data);

    String signUp(SignUpData data);

    List<User> list();

    User getById(String userId);
}
