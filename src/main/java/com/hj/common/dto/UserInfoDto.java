package com.hj.common.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserInfoDto {

    private String userId;

    private String userPassword;

    private String userIp;

    private String userName;

    private LocalDateTime registerTime;

    private String userPhone;

    private String userRole;

    private String userStatus;

    private LocalDateTime userFreezeTime;

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
}
