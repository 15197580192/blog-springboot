package com.hj.entity;

import java.time.LocalDateTime;
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
 * @since 2021-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Access implements Serializable {

    private static final long serialVersionUID = 1L;

    private String accesserId;

    private Long blogId;

    private LocalDateTime accessTime;

    private String accessType;

    private String accesserIp;

    @TableField("access_backup_1")
    private String accessBackup1;

    @TableField("access_backup_2")
    private String accessBackup2;

    @TableField("access_backup_2")
    private String accessBackup3;


}
