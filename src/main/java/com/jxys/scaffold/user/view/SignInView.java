package com.jxys.scaffold.user.view;

import com.jxys.scaffold.user.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SignInView {
    private String accessToken;
    private String refreshToken;
    private User user;
}