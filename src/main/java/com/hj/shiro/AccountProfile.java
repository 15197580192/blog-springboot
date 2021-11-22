package com.hj.shiro;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AccountProfile implements Serializable {

    private String userId;

    private String userIp;

    private String userName;

    private LocalDateTime registerTime;

    private String userPhone;

    private String userRole;

    private String userStatus;

    private LocalDateTime userFreezeTime;

    private String userBackup1;

    private String userBackup2;

    private String userBackup3;
}
