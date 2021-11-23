package com.hj.common.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class ChangePasswordDto implements Serializable {
    @NotBlank(message = "用户名不能为空")
    private String userId;
    @NotBlank(message = "密码不能为空")
    private String userPassword;
    @NotBlank(message = "新密码不能为空")
    private String userNewPassword;
}
