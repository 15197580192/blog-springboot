package com.hj.common.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class FindCodeDto {
    @NotBlank(message = "用户名不能为空")
    private String userId;

    @NotBlank(message = "新密码不能为空")
    public String newPassword;

    @NotBlank(message = "验证码不能为空")
    public String code;

    public static String checkId;
}
