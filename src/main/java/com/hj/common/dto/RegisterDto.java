package com.hj.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterDto {
    @NotBlank(message = "用户名不能为空")
    private String userId;

    @NotBlank(message = "密码不能为空")
    private String userPassword;

}
