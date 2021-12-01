package com.hj.entity;

import java.time.LocalDateTime;
import java.sql.Blob;
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
 * @since 2021-11-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AllBlog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long blogId;

    private String userId;

    private LocalDateTime blogPublishTime;

    private Long blogLikeCount;

    private Long blogCommentCount;

    private Long blogViewCount;

    private Long blogCollectCount;

    @TableField("user_nick_name")
    private String userNickname;

    @TableField("blog_backup_1")
    private String blogBackup1;
    @TableField("blog_backup_2")
    private String blogBackup2;
    @TableField("blog_backup_3")
    private String blogBackup3;

    private String blogTitle;

    private String blogContent;

    private Blob blogCover;

    private String blogReleaseType;

    private String blogStatus;

    private LocalDateTime blogDeleteTime;
    @TableField("blog_details_backup_1")
    private String blogDetailsBackup1;
    @TableField("blog_details_backup_2")
    private String blogDetailsBackup2;
    @TableField("blog_details_backup_3")
    private String blogDetailsBackup3;


}
