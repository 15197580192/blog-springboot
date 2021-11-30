package com.hj.common.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CommentDto implements Serializable {

    private Long blogId;

    private Long commentId;

    @NotBlank(message = "用户Id不能为空")
    private String userId;

    private Long commentLikeCount;

    private Long commentReportCount;

    private LocalDateTime commentDate;

    @NotBlank(message = "评论内容不能为空")
    private String commentContent;

    private Long parentCommentId;
}
