package com.hj.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author hzy
 * @since 2021-11-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AllUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String userIp;

    private String userName;

    private String userPassword;

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

    private String userProfilePhoto;

    private String userNickname;

    private String userSex;

    private LocalDate userBirth;

    private String userTown;

    private String userAddress;

    private String userMarry;

    private String userPosition;

    private String userUnit;

    private String userSignature;
    @TableField("user_backup_info_1")
    private String userBackupInfo1;
    @TableField("user_backup_info_2")
    private String userBackupInfo2;
    @TableField("user_backup_info_3")
    private String userBackupInfo3;


}
