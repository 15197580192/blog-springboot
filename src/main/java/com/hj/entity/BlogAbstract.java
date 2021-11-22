package com.hj.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 博客摘要表
 * </p>
 *
 * @author hzy
 * @since 2021-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogAbstract implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "blog_id", type = IdType.AUTO)
    private Long blogId;

    private String useUserId;

    private LocalDateTime blogPublishTime;

    private Long blogLikeCount;

    private Long blogCommentCount;

    private Long blogViewCount;

    private Long blogCollectCount;

    @TableField("blog_backip_1")
    private String blogBackip1;
    @TableField("blog_backup_2")
    private String blogBackup2;
    @TableField("blog_backup_3")
    private String blogBackup3;


}
