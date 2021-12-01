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
public class Praise implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long blogId;

    private Long commentId;

    private String praiserId;

    private LocalDateTime praiseTime;

    private String praiseType;

    @TableField("praise_backup_1")
    private String praiseBackup1;

    @TableField("praise_backup_2")
    private String praiseBackup2;

    @TableField("praise_backup_3")
    private String praiseBackup3;


}
