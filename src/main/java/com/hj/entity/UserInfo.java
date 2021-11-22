package com.hj.entity;

import java.time.LocalDate;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author hzy
 * @since 2021-11-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String useUserId;

    private String userId;

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

    @TableField("user_backup_info_1")
    private String userBackupInfo2;

    @TableField("user_backup_info_1")
    private String userBackupInfo3;


}
