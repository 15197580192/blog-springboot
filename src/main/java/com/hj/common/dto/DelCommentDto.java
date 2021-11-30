package com.hj.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class DelCommentDto implements Serializable {
    private Long blogId;

//    @NotBlank(message = "评论Id不能为空")
    private Long commentId;

    @NotBlank(message = "用户Id不能为空")
    private String userId;

    private Long commentLikeCount;

    private Long commentReportCount;

    private LocalDateTime commentDate;

    private String commentContent;

    private Long parentCommentId;
}
