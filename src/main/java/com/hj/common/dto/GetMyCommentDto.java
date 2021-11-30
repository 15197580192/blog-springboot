package com.hj.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class GetMyCommentDto implements Serializable {
    private Long blogId;

    private String blogTitle;

    private Long commentId;

    private String userId;

    private Long commentLikeCount;

    private Long commentReportCount;

    private LocalDateTime commentDate;

    private String commentContent;

    private Long parentCommentId;
}
