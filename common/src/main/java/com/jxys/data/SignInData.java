package com.jxys.data;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * 登录实体
 *
 * @author 李建
 */
@Data
@Accessors(chain = true)
public class SignInData {
    @NotBlank
    private String name;
    @NotBlank
    private String password;
}
