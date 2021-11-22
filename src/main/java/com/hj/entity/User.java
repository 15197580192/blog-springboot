package com.hj.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 
 * </p>
 *
 * @author hzy
 * @since 2021-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "用户名不能为空")
    @TableId("user_id")
    private String userId;

    @NotBlank(message = "密码不能为空")
    @TableField("user_password")
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
