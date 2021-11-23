package com.hj.entity;

import java.time.LocalDateTime;
import java.sql.Blob;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 博客详情表
 * </p>
 *
 * @author hzy
 * @since 2021-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "blog_id")
    private Long blogId;

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
