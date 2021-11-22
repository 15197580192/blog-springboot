package com.hj.common.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class LoginDto implements Serializable {
    @NotBlank(message = "用户名不能为空")
    private String userId;

    @NotBlank(message = "密码不能为空")
    private String userPassword;

    private String userIp;

    private String userName;

    private LocalDateTime registerTime;

    private String userPhone;

    private String userRole;

    private String userStatus;

    private LocalDateTime userFreezeTime;

    @TableField("user_backup_1")
    private String userBackup1;

    @TableField("user_backup_2")
    private String userBackup2;

    @TableField("user_backup_3")
    private String userBackup3;
}
