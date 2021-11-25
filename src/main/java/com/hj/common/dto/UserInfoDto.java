package com.hj.common.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserInfoDto {
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
}
