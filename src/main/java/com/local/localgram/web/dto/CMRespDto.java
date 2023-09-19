package com.local.localgram.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/*
  공통 응답 DTO
  전역적으로 사용하는 클래스임으로 제네릭타입 사용
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CMRespDto<T> {
    private int code; // 1(성공), -1(실패)
    private String message;
    private T data;
}
