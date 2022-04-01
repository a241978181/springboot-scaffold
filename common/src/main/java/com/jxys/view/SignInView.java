package com.jxys.view;

import com.jxys.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SignInView {
    private String accessToken;
    private String refreshToken;
    private User user;
}