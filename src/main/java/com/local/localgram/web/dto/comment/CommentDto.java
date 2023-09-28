package com.local.localgram.web.dto.comment;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/*
*   @NotBlank : null, "", " "(빈공백) 허용X
*   @NotEmpty : null, "" 허용X
*   @NotNull : 모든 데이터 타입에 대해 null 허용X
* */
@Data
public class CommentDto {
    @NotBlank
    private String content;
    @NotNull // null 체크
    private Integer imageId;

}

