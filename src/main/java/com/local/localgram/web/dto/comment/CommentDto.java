package com.local.localgram.web.dto.comment;

import lombok.Data;

@Data
public class CommentDto {
    private String content;
    private int imageId;

    // toEntity가 필요없음
}