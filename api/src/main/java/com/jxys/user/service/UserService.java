package com.jxys.user.service;

import com.jxys.data.SignInData;
import com.jxys.data.SignUpData;
import com.jxys.entity.User;
import com.jxys.view.SignInView;

import java.util.List;

public interface UserService{
    SignInView signIn(SignInData data);

    String signUp(SignUpData data);

    List<User> list();

    User getById(String userId);
}
