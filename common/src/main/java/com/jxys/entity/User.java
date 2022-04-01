package com.jxys.entity;

import com.jxys.base.entity.PojoParent;
import com.jxys.data.SignUpData;
import lombok.Data;

@Data
public class User extends PojoParent {
    private String name;
    private String password;
    private String email;

    public User() {

    }

    public User(SignUpData signUpData) {
        this.name=signUpData.getName();
        this.password=signUpData.getPassword();
        this.email=signUpData.getEmail();
    }
}