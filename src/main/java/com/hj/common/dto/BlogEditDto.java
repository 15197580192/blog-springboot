package com.hj.common.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDateTime;

@Data
public class BlogEditDto implements Serializable {

    private Long blogId;

    @NotBlank(message = "标题不能为空")
    private String blogTitle;

    @NotBlank(message = "内容不能为空")
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
