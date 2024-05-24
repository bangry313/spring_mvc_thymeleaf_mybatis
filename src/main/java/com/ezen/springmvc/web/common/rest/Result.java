package com.ezen.springmvc.web.common.rest;

import lombok.*;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class Result {
    private HttpStatus status;
    private String message;
    private Object data;
}
