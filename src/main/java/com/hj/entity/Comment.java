package com.hj.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author hzy
 * @since 2021-11-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long blogId;

    @TableId(value = "comment_id")
    private Long commentId;

    private String userId;

    private Long commentLikeCount;

    private Long commentReportCount;

    private LocalDateTime commentDate;

    private String commentContent;

    private Long parentCommentId;

    @TableField("comment_backup_1")
    private String commentBackup1;
    @TableField("comment_backup_2")
    private String commentBackup2;
    @TableField("comment_backup_3")
    private String commentBackup3;


}
