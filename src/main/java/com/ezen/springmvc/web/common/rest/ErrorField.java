package com.ezen.springmvc.web.common.rest;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class ErrorField {
    private String objectName;
    private String field;
    private String message;

}
