package com.hj.entity;

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
 * @since 2021-11-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AllComment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long blogId;

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

    private String blogTitle;


}
