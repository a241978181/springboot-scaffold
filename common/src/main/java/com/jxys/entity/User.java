package com.jxys.entity;

import com.jxys.base.entity.PojoParent;
import com.jxys.data.SignUpData;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 用户实体类
 *
 * @author a2417
 */
@Data
public class User extends PojoParent {
    /**
     * 姓名
     */
    @NotBlank
    @Size(max = 16)
    private String name;
    /**
     * 密码
     */
    @NotBlank
    @Size(max = 32)
    private String password;
    /**
     * 邮箱
     */
    @Size(max = 32)
    @Email
    private String email;
    /**
     * 手机号码
     */
    @Size(max = 13)
    @Pattern(regexp = "(\\+\\d+)?1[34578]\\d{9}$", message = "手机号码格式不正确")
    private String phoneNumber;

    /**
     * 身份证号
     */
    @NotBlank
    @Pattern(regexp = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}", message = "身份证号码格式不正确")
    private String idNumber;

    public User() {

    }

    public User(SignUpData signUpData) {
        this.name = signUpData.getName();
        this.password = signUpData.getPassword();
        this.email = signUpData.getEmail();
    }
}